package com.lbank.open.connector.client.old.impl.spot;

import com.lbank.open.connector.client.enums.HttpMethod;
import com.lbank.open.connector.client.utils.ParameterChecker;
import com.lbank.open.connector.client.utils.ProxyAuth;
import com.lbank.open.connector.client.utils.RequestHandler;
import com.lbank.open.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;

import java.util.Map;

public class Wallet {


    private final String baseUrl;
    private final RequestHandler requestHandler;


    public Wallet(String baseUrl, String apiKey, String secretKey, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
    }

    public Wallet(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
    }


    private final String USER_INFO = "/v2/user_info.do";


    public String userInfo() {
        return requestHandler.sendSignedRequest(baseUrl, USER_INFO, null, HttpMethod.POST);
    }


    private final String GET_DEPOSIT_ADDRESS = "/v2/get_deposit_address.do";


    public String getDepositAddress(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "assetCode", String.class);
        return requestHandler.sendSignedRequest(baseUrl, GET_DEPOSIT_ADDRESS, parameters, HttpMethod.POST);
    }


    private final String DEPOSIT_HISTORY = "/v2/deposit_history.do";


    public String depositHistory(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, DEPOSIT_HISTORY, parameters, HttpMethod.POST);
    }


    private final String WITHDRAW = "/v2/withdraw.do";


    public String withdraw(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "account", String.class);
        ParameterChecker.checkParameter(parameters, "assetCode", String.class);
        ParameterChecker.checkParameter(parameters, "amount", String.class);
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAW, parameters, HttpMethod.POST);
    }


    private final String WITHDRAW_CANCEL = "/v2/withdrawCancel.do";


    public String withdrawCancel(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "withdrawId", String.class);
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAW_CANCEL, parameters, HttpMethod.POST);
    }


    private final String WITHDRAWS = "/v2/withdraws.do";


    public String withdraws(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "status", String.class);
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAWS, parameters, HttpMethod.POST);
    }

}
