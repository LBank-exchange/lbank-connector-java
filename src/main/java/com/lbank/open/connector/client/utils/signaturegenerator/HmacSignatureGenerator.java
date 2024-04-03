package com.lbank.open.connector.client.utils.signaturegenerator;

import com.lbank.open.connector.client.utils.ParameterChecker;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public final class HmacSignatureGenerator implements SignatureGenerator {
    private static final Logger logger = LoggerFactory.getLogger(HmacSignatureGenerator.class);

    private static final String HMAC_SHA256 = "HmacSHA256";

    private final String apiSecret;

    public HmacSignatureGenerator(String apiSecret) {
        ParameterChecker.checkParameterType(apiSecret, String.class, "apiSecret");
        this.apiSecret = apiSecret;
    }

    @Override
    public String getSignMethod() {
        return HMAC_SHA256;
    }

    @Override
    public String getSignature(String data) {
        return this.sha256Hmac(DigestUtils.md5Hex(data).toUpperCase(), apiSecret);
    }


    private String sha256Hmac(String message, String secret) {
        try {
            Mac sha256Hmac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256);
            sha256Hmac.init(secretKey);
            byte[] bytes = sha256Hmac.doFinal(message.getBytes());
            return byteArrayToHexString(bytes);
        } catch (Exception e) {
            throw new RuntimeException("hmac sign error");
        }
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
}
