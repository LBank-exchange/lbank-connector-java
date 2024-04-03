package com.lbank.open.connector.client.utils;

import com.lbank.open.connector.client.exceptions.LbankClientException;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import com.lbank.open.connector.client.exceptions.LbankServerException;
import com.lbank.open.connector.client.utils.httpclient.HttpClientSingleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

public final class ResponseHandler {
    private static OkHttpClient client;
    private static final int HTTP_STATUS_CODE_400 = 400;
    private static final int HTTP_STATUS_CODE_499 = 499;
    private static final int HTTP_STATUS_CODE_500 = 500;

    private ResponseHandler() {
    }

    public static String handleResponse(Request request, ProxyAuth proxy) {
        client = HttpClientSingleton.getHttpClient(proxy);
        try (Response response = client.newCall(request).execute()) {
            if (null == response) {
                throw new LbankServerException("[ResponseHandler] No response from server");
            }

            String responseAsString = getResponseBodyAsString(response.body());

            if (response.code() >= HTTP_STATUS_CODE_400 && response.code() <= HTTP_STATUS_CODE_499) {
                throw handleErrorResponse(responseAsString, response.code());
            } else if (response.code() >= HTTP_STATUS_CODE_500) {
                throw new LbankServerException(responseAsString, response.code());
            }

            return responseAsString;
        } catch (IOException | IllegalStateException e) {
            String exceptionMsg = "OKHTTP Error: ";
            if (proxy != null) {
                if ((e.getClass().equals(ConnectException.class))) {
                    exceptionMsg = "Proxy Connection Error: ";
                } else if ((e.getClass().equals(UnknownHostException.class))) {
                    exceptionMsg = "Proxy Unknown Host Error: ";
                }
            }
            throw new LbankConnectorException("[ResponseHandler] " + exceptionMsg + e.getMessage());
        }
    }

//    private static String getLimitUsage(Response response, String resposeBodyAsString) {
//        JSONObject json = new JSONObject();
//        json.put("x-sapi-used-ip-weight-1m", response.header("X-SAPI-USED-IP-WEIGHT-1M"));
//        json.put("x-sapi-used-uid-weight-1m", response.header("X-SAPI-USED-UID-WEIGHT-1M"));
//        json.put("x-mbx-used-weight", response.header("x-mbx-used-weight"));
//        json.put("x-mbx-used-weight-1m", response.header("x-mbx-used-weight-1m"));
//        json.put("data", resposeBodyAsString);
//
//        return json.toString();
//    }

    private static LbankClientException handleErrorResponse(String responseBody, int responseCode) {
        try {
            String errorMsg = JSONParser.getJSONStringValue(responseBody, "msg");
            int errorCode = JSONParser.getJSONIntValue(responseBody, "code");
            return new LbankClientException(responseBody, errorMsg, responseCode, errorCode);
        } catch (JSONException e) {
            throw new LbankClientException(responseBody, responseCode);
        }
    }

    private static String getResponseBodyAsString(ResponseBody body) throws IOException {
        if (null != body) {
            return body.string();
        } else {
            return "";
        }
    }
}
