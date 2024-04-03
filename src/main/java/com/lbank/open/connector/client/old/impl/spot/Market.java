package com.lbank.open.connector.client.old.impl.spot;

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


    private final String WITHDRAW_CONFIGS = "/v2/withdrawConfigs.do";


    public String withdrawConfigs() {
        return requestHandler.sendPublicRequest(baseUrl, WITHDRAW_CONFIGS, null, HttpMethod.GET);
    }


    private final String TICKER_24HR = "/v2/ticker/24hr.do";


    public String ticker24hr(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TICKER_24HR, parameters, HttpMethod.GET);
    }


    private final String ETF_TICKER_24HR = "/v2/etfTicker/24hr.do";


    public String eftTicker24hr(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, ETF_TICKER_24HR, parameters, HttpMethod.GET);
    }


    private final String TICKER = "/v2/ticker.do";


    public String ticker(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, TICKER, parameters, HttpMethod.GET);
    }


    private final String DEPTH = "/v2/depth.do";


    public String depth(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "size", Integer.class);
        return requestHandler.sendPublicRequest(baseUrl, DEPTH, parameters, HttpMethod.GET);
    }



    private final String INCR_DEPTH = "/v2/incrDepth.do";


    public String incrDepth(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendPublicRequest(baseUrl, INCR_DEPTH, parameters, HttpMethod.GET);
    }


    private final String TRADES = "/v2/trades.do";


    public String trades(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "size", Integer.class);
        return requestHandler.sendPublicRequest(baseUrl, TRADES, parameters, HttpMethod.GET);
    }


    private final String KLINE = "/v2/kline.do";


    public String kline(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "size", Integer.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        ParameterChecker.checkParameter(parameters, "time", String.class);

        return requestHandler.sendPublicRequest(baseUrl, KLINE, parameters, HttpMethod.GET);
    }












}
