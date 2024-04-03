package com.lbank.open.connector.client.impl.websocketapi;

import com.lbank.open.connector.client.utils.websocketapi.WebSocketApiRequestHandler;
import org.json.JSONObject;


public class WebSocketApiGeneral implements WebSocketApiModule {
    private WebSocketApiRequestHandler handler;

    public WebSocketApiGeneral(WebSocketApiRequestHandler handler) {
        this.handler = handler;
    }


    public void ping(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


    public void pong(JSONObject parameters) {
        this.handler.publicRequest(parameters);
    }


}
