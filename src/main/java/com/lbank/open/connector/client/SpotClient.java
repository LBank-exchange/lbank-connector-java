package com.lbank.open.connector.client;

import com.lbank.open.connector.client.exceptions.LbankClientException;
import com.lbank.open.connector.client.exceptions.LbankConnectorException;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import com.lbank.open.connector.client.impl.spot.Market;
import com.lbank.open.connector.client.impl.spot.Subscribe;
import com.lbank.open.connector.client.impl.spot.Trade;
import com.lbank.open.connector.client.impl.spot.Wallet;
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
            throw new LbankConnectorException("sign method invalid");
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


    Subscribe createSubscribe();


}
