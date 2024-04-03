package examples.spot.subscribe;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class RefreshKeyExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("subscribeKey", "4027b6ac2ac1ef6f96748997b82e50cffacce45fd7ca105d1d075cbe38cc3baf");

        String result = client.createSubscribe().refreshKey(parameters);

        System.out.println(result);

    }
}
