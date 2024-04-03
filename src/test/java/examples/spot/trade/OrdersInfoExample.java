package examples.spot.trade;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.Map;

public class OrdersInfoExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "eth_btc");
        parameters.put("orderId", "79d61f61-120a-4845-bcdd-a63e22c411bc");
//        parameters.put("origClientOrderId", "79d61f61-120a-4845-bcdd-a63e22c411bc");


        String result = client.createTrade().ordersInfo(parameters);

        System.out.println(result);
    }
}
