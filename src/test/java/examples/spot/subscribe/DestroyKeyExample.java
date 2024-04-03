package examples.spot.subscribe;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class DestroyKeyExample {


    public static void main(String[] args) {


        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("subscribeKey", "daf1adce73bfdaa1c3393755756b7cc0e1933a79be6d5ce9d372facfc93a1dda");

        String result = client.createSubscribe().destroyKey(parameters);

        System.out.println(result);


    }
}
