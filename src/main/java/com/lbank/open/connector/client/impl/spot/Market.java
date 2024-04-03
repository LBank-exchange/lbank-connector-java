package com.lbank.open.connector.client.impl.spot;

import com.lbank.open.connector.client.enums.HttpMethod;
import com.lbank.open.connector.client.utils.ParameterChecker;
import com.lbank.open.connector.client.utils.ProxyAuth;
import com.lbank.open.connector.client.utils.RequestHandler;

import java.util.Map;

public class Market {
    private final String baseUrl;
    private final RequestHandler requestHandler;

    public Market(String baseUrl, String apiKey, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, proxy);
    }


    private final String PING = "/v2/supplement/system_ping.do";


    public String ping() {
        return requestHandler.sendPublicRequest(baseUrl, PING, null, HttpMethod.POST);
    }


    private final String CURRENCY_PAIRS = "/v2/currencyPairs.do";

    public String currencyPairs() {
        return requestHandler.sendPublicRequest(baseUrl, CURRENCY_PAIRS, null, HttpMethod.GET);
    }


    private final String ACCURACY = "/v2/accuracy.do";


    public String accuracy() {
        return requestHandler.sendPublicRequest(baseUrl, ACCURACY, null, HttpMethod.GET);
    }


    private final String USD_TO_CNY = "/v2/usdToCny.do";


    public String usdToCny() {
        return requestHandler.sendPublicRequest(baseUrl, USD_TO_CNY, null, HttpMethod.GET);
    }


    private final String TIMESTAMP = "/v2/timestamp.do";


    public String timestamp() {
        return requestHandler.sendPublicRequest(baseUrl, TIMESTAMP, null, HttpMethod.GET);
    }


//    private final String DEPTH = "/v2/supplement/incrDepth.do";
    private final String DEPTH = "/v2/depth.do";


    public String depth(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "size", Integer.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET);
    }


    private final String TRADES = "/v2/supplement/trades.do";


    public String trades(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "size", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET);
    }


    private final String PRICE = "/v2/supplement/ticker/price.do";

    public String price(Map<String, Object> parameters) {
        return requestHandler.sendPublicRequest(baseUrl, PRICE, parameters, HttpMethod.GET);
    }


    private final String BOOK_TICKER = "/v2/supplement/ticker/bookTicker.do";


    public String bookTicker(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, BOOK_TICKER, parameters, HttpMethod.GET);
    }

}
