package com.lbank.open.connector.client;


import com.lbank.open.connector.client.impl.WebSocketApiClientImpl;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiAccount;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiGeneral;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiMarket;
import com.lbank.open.connector.client.impl.websocketapi.WebSocketApiTrade;
import com.lbank.open.connector.client.utils.websocketcallback.*;

public interface WebSocketApiClient {

    static WebSocketApiClient getInstance(String baseUrl) {
        return new WebSocketApiClientImpl(baseUrl);
    }

    void connect(WebSocketMessageCallback onMessageCallback);
    void connect(WebSocketOpenCallback onOpenCallback, WebSocketMessageCallback onMessageCallback, WebSocketClosingCallback onClosingCallback, WebSocketClosedCallback onClosedCallback, WebSocketFailureCallback onFailureCallback);
    void close();
    WebSocketApiMarket market();

    WebSocketApiGeneral general();

    WebSocketApiTrade trade();


    WebSocketApiAccount account();
}
