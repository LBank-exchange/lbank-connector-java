package com.lbank.open.connector.client.impl.websocketapi;

import com.lbank.open.connector.client.utils.websocketapi.WebSocketApiRequestHandler;
import org.json.JSONObject;


public class WebSocketApiTrade implements WebSocketApiModule {
    private WebSocketApiRequestHandler handler;

    public WebSocketApiTrade(WebSocketApiRequestHandler handler) {  
        this.handler = handler;
    }


    public void orderUpdate(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


}