package com.lbank.open.connector.client.utils;

import com.lbank.open.connector.client.enums.HttpMethod;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public final class RequestBuilder {
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static final String USER_AGENT = "lbank-connector-java/3.2.0";
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

    private RequestBuilder() {
    }
    public static Request buildPublicRequest(String fullUrl, HttpMethod httpMethod) {
        try {
            final Request.Builder requestBuilder = new Request.Builder().addHeader("User-Agent", USER_AGENT).addHeader("Content-Type", CONTENT_TYPE).url(fullUrl);
            switch (httpMethod) {
                case POST:
                    return requestBuilder.post(RequestBody.create("", JSON_TYPE)).build();
                case GET:
                    return requestBuilder.get().build();
                default:
                    throw new LbankConnectorException("Invalid HTTP method: " + httpMethod);
            }
        } catch (IllegalArgumentException e) {
            throw new LbankConnectorException("Invalid URL: " + e.getMessage());
        }
    }

    public static Request buildApiKeyRequest(String fullUrl, HttpMethod httpMethod, String timestamp, String echostr, String signMethod) {
        try {
            final Request.Builder requestBuilder = new Request.Builder()
                    .addHeader("User-Agent", USER_AGENT)
                    .addHeader("Content-Type", CONTENT_TYPE)
                    .addHeader("timestamp", timestamp)
                    .addHeader("echostr", echostr)
                    .addHeader("signature_method", signMethod)
                    .url(fullUrl);
            switch (httpMethod) {
                case POST:
                    return requestBuilder.post(RequestBody.create("", JSON_TYPE)).build();
                case GET:
                    return requestBuilder.get().build();
                default:
                    throw new LbankConnectorException("Invalid HTTP method: " + httpMethod);
            }
        } catch (IllegalArgumentException e) {
            throw new LbankConnectorException("Invalid URL: " + e.getMessage());
        }
    }

    public static Request buildWebSocketRequest(String fullUrl) {
        return new Request.Builder().url(fullUrl).build();
    }
}
