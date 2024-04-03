package com.lbank.open.connector.client.impl;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.spot.Market;
import com.lbank.open.connector.client.impl.spot.Subscribe;
import com.lbank.open.connector.client.impl.spot.Trade;
import com.lbank.open.connector.client.impl.spot.Wallet;
import com.lbank.open.connector.client.utils.ProxyAuth;
import com.lbank.open.connector.client.utils.signaturegenerator.SignatureGenerator;

public class SpotClientImpl implements SpotClient {

    private String apiKey;
    private SignatureGenerator signatureGenerator;
    private String baseUrl;
    //    private boolean showLimitUsage = false;
    private ProxyAuth proxy = null;

    public SpotClientImpl(String baseUrl) {
        this("", null, baseUrl);
    }

    public SpotClientImpl(String apiKey, SignatureGenerator signatureGenerator, String baseUrl) {
        this.apiKey = apiKey;
        this.signatureGenerator = signatureGenerator;
        this.baseUrl = baseUrl;
    }


    @Override
    public void setProxy(ProxyAuth proxy) {
        this.proxy = proxy;
    }

    @Override
    public void unsetProxy() {
        this.proxy = null;
    }

    @Override
    public Market createMarket() {
        return new Market(baseUrl, apiKey, proxy);
    }

    @Override
    public Wallet createWallet() {
        return new Wallet(baseUrl, apiKey, signatureGenerator, proxy);
    }


    @Override
    public Trade createTrade() {
        return new Trade(baseUrl, apiKey, signatureGenerator, proxy);
    }


    @Override
    public Subscribe createSubscribe() {
        return new Subscribe(baseUrl, apiKey, signatureGenerator, proxy);
    }
}
