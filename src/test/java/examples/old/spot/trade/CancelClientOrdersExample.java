package examples.old.spot.trade;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class CancelClientOrdersExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put("customer_id", "9bc71422-58dc-4173-8b9c-462f47f0bfe1");
        parameters.put("symbol", "eth_btc");

        String result = client.createTrade().cancelClientOrders(parameters);

        System.out.println(result);
    }
}
