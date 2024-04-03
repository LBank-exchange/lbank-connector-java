package com.lbank.open.connector.client.impl.spot;

import com.lbank.open.connector.client.enums.HttpMethod;
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

    private final String SYSTEM_STATUS = "/v2/supplement/system_status.do";


    public String systemStatus() {
        return requestHandler.sendPublicRequest(baseUrl, SYSTEM_STATUS, null, HttpMethod.POST);
    }


    private final String USER_INFO = "/v2/supplement/user_info.do";


    public String userInfo(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, USER_INFO, parameters, HttpMethod.POST);
    }


    private final String WITHDRAW = "/v2/supplement/withdraw.do";


    public String withdraw(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAW, parameters, HttpMethod.POST);
    }


    private final String DEPOSIT_HISTORY = "/v2/supplement/deposit_history.do";


    public String depositHistory(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, DEPOSIT_HISTORY, parameters, HttpMethod.POST);
    }


    private final String WITHDRAW_HISTORY = "/v2/supplement/withdraws.do";


    public String withdrawHistory(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, WITHDRAW_HISTORY, parameters, HttpMethod.POST);
    }


    private final String GET_DEPOSIT_ADDRESS = "/v2/supplement/get_deposit_address.do";


    public String getDepositAddress(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, GET_DEPOSIT_ADDRESS, parameters, HttpMethod.POST);
    }


    private final String ASSET_DETAIL = "/v2/supplement/asset_detail.do";


    public String assetDetail(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, ASSET_DETAIL, parameters, HttpMethod.POST);
    }


    private final String TRADE_FEE = "/v2/supplement/customer_trade_fee.do";


    public String tradeFee(Map<String, Object> parameters) {
        return requestHandler.sendSignedRequest(baseUrl, TRADE_FEE, parameters, HttpMethod.POST);
    }


    private final String API_RESTRICTIONS = "/v2/supplement/api_Restrictions.do";


    public String apiRestrictions() {
        return requestHandler.sendSignedRequest(baseUrl, API_RESTRICTIONS, null, HttpMethod.POST);
    }
}
