package com.lbank.open.connector.client.impl;


import com.lbank.open.connector.client.enums.Category;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import com.lbank.open.connector.client.impl.websocketapi.*;
import com.lbank.open.connector.client.utils.websocketapi.WebSocketApiRequestHandler;

import java.util.Locale;

public final class WebSocketApiModuleFactory {
    private static WebSocketApiMarket wsApiMarket;


    private static WebSocketApiGeneral wsApiGeneral;


    private static WebSocketApiTrade wsApiTrade;


    private static WebSocketApiAccount wsApiAccount;

    private WebSocketApiModuleFactory() {
        // Private constructor to prevent instantiation
    }

    private interface ModuleCreator {
        WebSocketApiModule create();
    }

    private static WebSocketApiModule obtainModule(WebSocketApiModule module, ModuleCreator creator) {
        if (module == null) {
            module = creator.create();
        }
        return module;
    }


    public static WebSocketApiModule build(Category category, WebSocketApiRequestHandler requestHandler) {
        switch (category) {
            case ACCOUNT:
                return obtainModule(wsApiAccount, () -> new WebSocketApiAccount(requestHandler));
            case AUTH:
//                return obtainModule(wsApiAuth, () -> new WebSocketApiAuth(requestHandler));
            case GENERAL:
                return obtainModule(wsApiGeneral, () -> new WebSocketApiGeneral(requestHandler));
            case MARKET:
                return obtainModule(wsApiMarket, () -> new WebSocketApiMarket(requestHandler));
            case TRADE:
                return obtainModule(wsApiTrade, () -> new WebSocketApiTrade(requestHandler));
            case USER_DATA_STREAM:
//                return obtainModule(wsApiUserDataStream, () -> new WebSocketApiUserDataStream(requestHandler));
            default:
                throw new LbankConnectorException("Unknown WebSocket API Category: " + category);
        }
    }

}