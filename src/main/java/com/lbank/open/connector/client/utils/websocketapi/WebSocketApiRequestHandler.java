package com.lbank.open.connector.client.utils.websocketapi;

import com.lbank.open.connector.client.enums.RequestType;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import com.lbank.open.connector.client.utils.JSONParser;
import com.lbank.open.connector.client.utils.ParameterChecker;
import com.lbank.open.connector.client.utils.UrlBuilder;
import com.lbank.open.connector.client.utils.WebSocketConnection;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.json.JSONObject;

public class WebSocketApiRequestHandler {
//    private final SignatureGenerator signatureGenerator;
//    private final String apiKey;
    private WebSocketConnection connection;

    public WebSocketApiRequestHandler(WebSocketConnection connection) {
        if (connection == null) {
            throw new LbankConnectorException("[WebSocketApiRequestHandler] WebSocketConnection cannot be null");
        }
        this.connection = connection;
//        this.apiKey = apiKey;
//        this.signatureGenerator = signatureGenerator;
    }

    public void publicRequest(JSONObject parameters) {
        this.request(RequestType.PUBLIC, parameters);
    }

//    public void apiRequest(JSONObject parameters) {
//        RequestType requestType = RequestType.PUBLIC;
//        this.request(requestType, parameters);
//    }

//    public void signedRequest(JSONObject parameters) {
//        RequestType requestType = this.connection.getSessionStatus() ? RequestType.PUBLIC : RequestType.SIGNED;
//        this.request(requestType, parameters);
//    }

    public void request(RequestType requestType, JSONObject parameters) {
//        Object requestId = ParameterChecker.processId(JSONParser.pullValue(parameters, "requestId"), "requestId");

        switch (requestType) {
            case PUBLIC:
                this.connection.send(parameters.toString());
                break;
//            case SIGNED:
//                ParameterChecker.checkParameterType(this.apiKey, String.class, "apiKey");
//                parameters = JSONParser.addKeyValue(parameters, "apiKey", this.apiKey);
//                if (!parameters.has("timestamp")) {
//                    parameters.put("timestamp", UrlBuilder.buildTimestamp());
//                }
//
//                // signature
//                ParameterChecker.checkParameterType(this.signatureGenerator, SignatureGenerator.class, "signatureGenerator");
//                String payload = UrlBuilder.joinQueryParameters(JSONParser.sortJSONObject(parameters));
//                String signature = this.signatureGenerator.getSignature(payload);
//                parameters.put("signature", signature);
//
//                this.connection.send(parameters.toString());
//                break;
            default:
                throw new LbankConnectorException("[WebSocketApiRequestHandler] Invalid request type: " + requestType);
        }
    }
}
