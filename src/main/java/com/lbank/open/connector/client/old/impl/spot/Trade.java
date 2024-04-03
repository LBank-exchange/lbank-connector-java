package com.lbank.open.connector.client.old.impl.spot;

import com.lbank.open.connector.client.enums.HttpMethod;
import com.lbank.open.connector.client.utils.ParameterChecker;
import com.lbank.open.connector.client.utils.ProxyAuth;
import com.lbank.open.connector.client.utils.RequestHandler;
import com.lbank.open.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;

import java.util.Map;

public class Trade {

    private final String baseUrl;
    private final RequestHandler requestHandler;


    public Trade(String baseUrl, String apiKey, String secretKey, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, new HmacSignatureGenerator(secretKey), proxy);
    }

    public Trade(String baseUrl, String apiKey, SignatureGenerator signatureGenerator, ProxyAuth proxy) {
        this.baseUrl = baseUrl;
        this.requestHandler = new RequestHandler(apiKey, signatureGenerator, proxy);
    }


    private final String CREATE_ORDER = "/v2/create_order.do";


    public String createOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "type", String.class);
        ParameterChecker.checkParameter(parameters, "price", String.class);
        ParameterChecker.checkParameter(parameters, "amount", String.class);
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CREATE_ORDER, parameters, HttpMethod.POST);
    }


    private final String BATCH_CREATE_ORDER = "/v2/batch_create_order.do";


    public String batchCreateOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "orders", String.class);
        return requestHandler.sendSignedRequest(baseUrl, BATCH_CREATE_ORDER, parameters, HttpMethod.POST);
    }


    private final String CANCEL_ORDER = "/v2/cancel_order.do";


    public String cancelOrder(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "order_id", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CANCEL_ORDER, parameters, HttpMethod.POST);
    }


    private final String CANCEL_CLIENT_ORDERS = "/v2/cancel_clientOrders.do";


    public String cancelClientOrders(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "customer_id", String.class);
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        return requestHandler.sendSignedRequest(baseUrl, CANCEL_CLIENT_ORDERS, parameters, HttpMethod.POST);
    }


    private final String ORDERS_INFO = "/v2/orders_info.do";


    public String ordersInfo(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "order_id", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDERS_INFO, parameters, HttpMethod.POST);
    }


    private final String ORDERS_INFO_HISTORY = "/v2/orders_info_history.do";


    public String ordersInfoHistory(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "current_page", String.class);
        ParameterChecker.checkParameter(parameters, "page_length", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDERS_INFO_HISTORY, parameters, HttpMethod.POST);
    }


    private final String ORDER_TRANSACTION_DETAIL = "/v2/order_transaction_detail.do";

    public String orderTransactionDetail(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "order_id", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDER_TRANSACTION_DETAIL, parameters, HttpMethod.POST);
    }


    private final String TRANSACTION_HISTORY = "/v2/transaction_history.do";


    public String transactionHistory(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "type", String.class);
        return requestHandler.sendSignedRequest(baseUrl, TRANSACTION_HISTORY, parameters, HttpMethod.POST);
    }


    private final String ORDERS_INFO_NO_DEAL = "/v2/orders_info_no_deal.do";


    public String ordersInfoNoDeal(Map<String, Object> parameters) {
        ParameterChecker.checkParameter(parameters, "symbol", String.class);
        ParameterChecker.checkParameter(parameters, "current_page", String.class);
        ParameterChecker.checkParameter(parameters, "page_length", String.class);
        return requestHandler.sendSignedRequest(baseUrl, ORDERS_INFO_NO_DEAL, parameters, HttpMethod.POST);
    }


}
