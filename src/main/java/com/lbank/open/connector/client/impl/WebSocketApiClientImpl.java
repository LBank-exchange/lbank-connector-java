package com.lbank.open.connector.client.impl;

import com.lbank.open.connector.client.WebSocketApiClient;
import com.lbank.open.connector.client.enums.Category;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiAccount;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiGeneral;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiMarket;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiTrade;
import com.lbank.open.connector.client.utils.RequestBuilder;
import com.lbank.open.connector.client.utils.WebSocketConnection;
import com.lbank.open.connector.client.utils.httpclient.WebSocketApiHttpClientSingleton;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;
import com.lbank.open.connector.client.utils.websocketapi.WebSocketApiRequestHandler;
import com.lbank.open.connector.client.utils.websocketcallback.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONObject;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WebSocketApiClientImpl implements WebSocketApiClient {
    private static final OkHttpClient client = WebSocketApiHttpClientSingleton.getHttpClient();
    private final String baseUrl;
    private final WebSocketOpenCallback noopOpenCallback = response -> { };
    private final WebSocketClosingCallback noopClosingCallback = (code, reason) -> { };
    private final WebSocketClosedCallback noopClosedCallback = (code, reason) -> { };
    private final WebSocketFailureCallback noopFailureCallback = (throwable, response) -> { };
    private WebSocketConnection connection;
    private WebSocketApiRequestHandler requestHandler;

    public WebSocketApiClientImpl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    private void checkRequestHandler() {
        if (this.requestHandler == null) {
            throw new LbankConnectorException("No WebSocket API connection to submit request. Please connect first.");
        }
    }

    @Override
    public void connect(WebSocketMessageCallback onMessageCallback) {
        connect(noopOpenCallback, onMessageCallback, noopClosingCallback, noopClosedCallback, noopFailureCallback);
    }

    @Override
    public void connect(WebSocketOpenCallback onOpenCallback, WebSocketMessageCallback onMessageCallback, WebSocketClosingCallback onClosingCallback, WebSocketClosedCallback onClosedCallback, WebSocketFailureCallback onFailureCallback) {
        Request request = RequestBuilder.buildWebSocketRequest(baseUrl);

        this.connection = new WebSocketConnection(onOpenCallback, onMessageCallback, onClosingCallback, onClosedCallback, onFailureCallback, request, client);
        this.requestHandler = new WebSocketApiRequestHandler(this.connection);
        this.connection.connect();


        //heartbeat
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        executorService.scheduleAtFixedRate(() -> {
            JSONObject params = new JSONObject();
            params.put("action", "ping");
            params.put("ping", UUID.randomUUID());
            this.general().ping(params);
        }, 0, 5, TimeUnit.SECONDS);

    }

    @Override
    public void close() {
        this.connection.close();
        client.dispatcher().executorService().shutdown();
    }


    @Override
    public WebSocketApiMarket market() {
        checkRequestHandler();
        return (WebSocketApiMarket) WebSocketApiModuleFactory.build(Category.MARKET, this.requestHandler);
    }


    @Override
    public WebSocketApiGeneral general() {
        checkRequestHandler();
        return (WebSocketApiGeneral) WebSocketApiModuleFactory.build(Category.GENERAL, this.requestHandler);
    }

    @Override
    public WebSocketApiTrade trade() {
        checkRequestHandler();
        return (WebSocketApiTrade) WebSocketApiModuleFactory.build(Category.TRADE, this.requestHandler);
    }

    @Override
    public WebSocketApiAccount account() {
        checkRequestHandler();
        return (WebSocketApiAccount) WebSocketApiModuleFactory.build(Category.ACCOUNT, this.requestHandler);
    }
}
