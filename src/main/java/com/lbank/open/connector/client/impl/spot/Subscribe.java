package com.lbank.open.connector.client.impl.spot;

import com.lbank.open.connector.client.enums.HttpMethod;
import com.lbank.open.connector.client.utils.ProxyAuth;
import com.lbank.open.connector.client.utils.RequestHandler;
import com.lbank.open.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;

import java.util.Map;

public class Subscribe {


    private final String baseUrl;
    private final RequestHandler requestHandler;


    public Subscribe(String baseUrl, String apiKey, String secretKey, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
    }

    public Subscribe(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
    }


    private final String GET_KEY = "/v2/subscribe/get_key.do";


    public String getKey() {
        return requestHandler.sendSignedRequest(baseUrl, GET_KEY, null, HttpMethod.POST);
    }


    private final String REFRESH_KEY = "/v2/subscribe/refresh_key.do";


    public String refreshKey(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, REFRESH_KEY, parameters, HttpMethod.POST);
    }


    private final String DESTROY_KEY = "/v2/subscribe/destroy_key.do";



    public String destroyKey(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, DESTROY_KEY, parameters, HttpMethod.POST);
    }





}
