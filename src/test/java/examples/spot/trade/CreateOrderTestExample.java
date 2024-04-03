package examples.spot.trade;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.Map;

public class CreateOrderTestExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "eth_btc");
        parameters.put("type", "buy");
        parameters.put("price", "100");
        parameters.put("amount", "10");



        String result = client.createTrade().createOrderTest(parameters);

        System.out.println(result);
    }
}
