package examples.old.spot.trade;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateOrderExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "eth_btc");
        parameters.put("type", "buy");
        parameters.put("price", "100");
        parameters.put("amount", "100");

        String result = client.createTrade().createOrder(parameters);

        System.out.println(result);
    }
}
