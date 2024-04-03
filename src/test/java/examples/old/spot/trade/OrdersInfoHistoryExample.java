package examples.old.spot.trade;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrdersInfoHistoryExample {


    public static void main(String[] args) {

        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "eth_btc");
        parameters.put("current_page", "1");
        parameters.put("page_length", "10");

        String result = client.createTrade().ordersInfoHistory(parameters);

        System.out.println(result);
    }
}
