package examples;

public final class PrivateConfig {
    private PrivateConfig() {
    }

    public static final String BASE_URL = "https://www.lbkex.net";


    public static final String WS_API_URL = "wss://www.lbkex.net/ws/V2/";


    public static final String SIGN_METHOD = "HmacSHA256";     /* HmacSHA256 & RSA */
//    public static final String SIGN_METHOD = "RSA";     /* HmacSHA256 & RSA */

    //hmac
    public static final String API_KEY = "d88202b1-b534-47e4-84ce-a898f8b79e8e";
    public static final String SECRET_KEY = "66D4B2A93C8E8B7ED03EDC39420A6F98";

    //rsa
//    public static final String API_KEY = "a8d8ba1a-fc00-496b-b771-d3cd34ba420c";
//    public static final String SECRET_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJszl5v82IkgeMoBWGWzZ9zHnEVLV6JnpkPAyXHY5s4HFdMm94avYXmfhTNpSewUWfDVeuylksIIR5r5DtlD05C1T7T4Un0RhiJakd5OL5+/uufsOJ90dCi3YpyLpZVpPbh25jSH3sr/RyPov1Vs+iKViAh1xjLCMLMtJuVEBg0NAgMBAAECgYBm0zuzIUXlNBpS26lRWBeL8sPaNRyOgNVrNiNAxk/EWHVhPAPqu+98wO9jLHLJcoJUSVvzxY8B9rJaPoBT+dgEi6JbBhKWc5DaXvxamEGCk3m+Vr3w52MTJnuL/9BXHYFMTGRKLlyKeJNm0Ri9d+cuGiffplef0iU6w576UBYKkQJBAP8uUItCaJVde4Mka1kQqjdlluDmairop+ulggHqtF5+bdut4yPhMO21LmC3EvrtX2renoqA+DI1sje2Kt8qrcsCQQCbsx+PM85btjx6/MHdWRl94kPbrjvD03ceDZp7mfZx7E1I7u8mNYmHqnFFh3RSL0Db/7IMVsMf5670HAEgcFWHAkB4m2xIFZAWoINi/VCbXOwGmMZR77VYKHRNRQGIZGTxkHHd9Xa4m5OjlhGgdobLAGwrt6JZNhGYsZZVDI2Awsw3AkEAgRsDtVPo7kVrWzQRcQC70ZV4XUXZiuTjZgkzqwAcyZR+9VYi+dT06IjdiiDqxGcoagRZWD/tZpxPh11dVc+wKwJAFZKlfevups9+N0OtyFStwlaexRovZqxBEQvYaLXfnpoQoU35CsuzitS21TZpW9OEmyYU8A8BfwCs9pl5TRb2AA==";

}
