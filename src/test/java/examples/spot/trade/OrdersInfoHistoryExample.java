package examples.spot.trade;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.Map;

public class OrdersInfoHistoryExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "eth_btc");
        parameters.put("current_page", "1");
        parameters.put("page_length", "100");
        parameters.put("status", "1");


        String result = client.createTrade().ordersInfoHistory(parameters);

        System.out.println(result);
    }
}
