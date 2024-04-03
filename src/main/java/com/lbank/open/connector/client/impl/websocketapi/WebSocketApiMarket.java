package com.lbank.open.connector.client.impl.websocketapi;

import com.lbank.open.connector.client.utils.websocketapi.WebSocketApiRequestHandler;
import org.json.JSONObject;


public class WebSocketApiMarket implements WebSocketApiModule {
    private WebSocketApiRequestHandler handler;

    public WebSocketApiMarket(WebSocketApiRequestHandler handler) {
        this.handler = handler;
    }


    public void kbar(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


    public void depth(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


    public void incrDepth(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


    public void tick(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


    public void trade(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }

}
