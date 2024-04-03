package com.lbank.open.connector.client.utils;


import com.lbank.open.connector.client.enums.HttpMethod;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import com.lbank.open.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.lbank.open.connector.client.utils.signaturegenerator.RsaSignatureGenerator;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class RequestHandler {
    private final String apiKey;
    private final SignatureGenerator signatureGenerator;
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private final ProxyAuth proxy;

    public RequestHandler(String apiKey, ProxyAuth proxy) {
        this(apiKey, null, proxy);
    }

    public RequestHandler(String apiKey, SignatureGenerator signatureGenerator, ProxyAuth proxy) {
        this.apiKey = apiKey;
        this.signatureGenerator = signatureGenerator;
        this.proxy = proxy;
    }

    public String sendPublicRequest(String baseUrl, String urlPath, Map<String, Object> parameters, HttpMethod httpMethod) {
        String fullUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        logger.info("{} {}", httpMethod, fullUrl);

        return ResponseHandler.handleResponse(RequestBuilder.buildPublicRequest(fullUrl, httpMethod), proxy);
    }

    public String sendApiRequest(String baseUrl, String urlPath, Map<String, Object> parameters, HttpMethod httpMethod) {
        if (null == apiKey || apiKey.isEmpty()) {
            throw new LbankConnectorException("[RequestHandler] API key cannot be null or empty!");
        }

        String fullUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        logger.info("{} {}", httpMethod, fullUrl);

        return ResponseHandler.handleResponse(RequestBuilder.buildApiKeyRequest(fullUrl, httpMethod, "", "", ""), proxy);
    }

    public String sendSignedRequest(String baseUrl, String urlPath, Map<String, Object> parameters, HttpMethod httpMethod) {
        if (signatureGenerator.getClass() == HmacSignatureGenerator.class && (null == apiKey || apiKey.isEmpty())) {
            throw new LbankConnectorException("[RequestHandler] Secret key/API key cannot be null or empty!");
        }
        if (signatureGenerator.getClass() == RsaSignatureGenerator.class && (null == apiKey || apiKey.isEmpty())) {
            throw new LbankConnectorException("[RequestHandler] Private key/API key cannot be null or empty!");
        }

        parameters = (parameters == null) ? new HashMap<>() : parameters;


        Map<String, Object> signMap = new TreeMap<>(parameters);

        String timestamp = UrlBuilder.buildTimestamp();
        String echostr = UrlBuilder.buildEchostr();
        String signMethod = signatureGenerator.getSignMethod();
        signMap.put("timestamp", timestamp);
        signMap.put("echostr", echostr);
        signMap.put("signature_method", signMethod);
        signMap.put("api_key", apiKey);


        parameters.put("api_key", apiKey);
        parameters.put("sign", this.signatureGenerator.getSignature(UrlBuilder.joinQueryParameters(signMap, true)));

        String fullUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters);
        logger.info("{} {}", httpMethod, fullUrl);

        return ResponseHandler.handleResponse(RequestBuilder.buildApiKeyRequest(fullUrl, httpMethod, timestamp, echostr, signMethod), proxy);
    }
}
