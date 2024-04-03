package com.lbank.open.connector.client.old;

import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import com.lbank.open.connector.client.old.impl.spot.Market;
import com.lbank.open.connector.client.old.impl.spot.Trade;
import com.lbank.open.connector.client.old.impl.spot.Wallet;
import com.lbank.open.connector.client.utils.ProxyAuth;
import com.lbank.open.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import com.lbank.open.connector.client.utils.signaturegenerator.RsaSignatureGenerator;

public interface SpotClient {


    static SpotClient getInstance(String apiKey, String secretKey, String baseUrl, String signMethod) {
        if ("HmacSHA256".equals(signMethod)) {
            return new SpotClientImpl(apiKey, new HmacSignatureGenerator(secretKey), baseUrl);
        } else if ("RSA".equals(signMethod)) {
            return new SpotClientImpl(apiKey, new RsaSignatureGenerator(secretKey), baseUrl);
        } else {
            return new SpotClientImpl(apiKey, new HmacSignatureGenerator(secretKey), baseUrl);
        }
    }

    static SpotClient getInstance(String baseUrl) {
        return new SpotClientImpl(baseUrl);
    }


    void setProxy(ProxyAuth proxy);
    void unsetProxy();


    Market createMarket();


    Wallet createWallet();


    Trade createTrade();
}
