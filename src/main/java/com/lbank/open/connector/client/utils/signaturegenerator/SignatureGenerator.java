package com.lbank.open.connector.client.utils.signaturegenerator;

public interface SignatureGenerator {
    String getSignature(String payload);


    String getSignMethod();
}
