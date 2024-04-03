package com.lbank.open.connector.client.utils.signaturegenerator;


import com.lbank.open.connector.client.utils.ParameterChecker;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public final class RsaSignatureGenerator implements SignatureGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RsaSignatureGenerator.class);


    private static final String RSA_SHA256 = "SHA256WithRSA";
    private static final String RSA = "RSA";
    private final String privateKey;

    public RsaSignatureGenerator(String privateKey) {
        ParameterChecker.checkParameterType(privateKey, String.class, "privateKey");
        this.privateKey = privateKey;
    }


    @Override
    public String getSignMethod() {
        return RSA;
    }

    @Override
    public String getSignature(String data) {
        return this.sign(DigestUtils.md5Hex(data).toUpperCase(), privateKey);
    }


    private String sign(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Signature signature = Signature.getInstance(RSA_SHA256);
            signature.initSign(priKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return new String(Base64.getEncoder().encode(signed));
        } catch (Exception e) {
            throw new RuntimeException("rsa sign error");
        }
    }


    private PrivateKey getPrivateKey(String key) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException("rsa get private key error");
        }
    }


}
