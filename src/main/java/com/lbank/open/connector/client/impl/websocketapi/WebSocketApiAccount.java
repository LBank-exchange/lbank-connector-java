package com.lbank.open.connector.client.impl.websocketapi;

import com.lbank.open.connector.client.utils.websocketapi.WebSocketApiRequestHandler;
import org.json.JSONObject;


public class WebSocketApiAccount implements WebSocketApiModule {
    
    private WebSocketApiRequestHandler handler;

    public WebSocketApiAccount(WebSocketApiRequestHandler handler) {
        this.handler = handler;
    }


    public void assetUpdate(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }



}
