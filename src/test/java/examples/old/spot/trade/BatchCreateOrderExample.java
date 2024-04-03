package examples.old.spot.trade;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class BatchCreateOrderExample {

    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new LinkedHashMap<>();

        parameters.put("orders", "[{\"symbol\":\"eth_btc\", \"type\":\"buy\", \"price\":\"100\", \"amount\":\"100\"}]");

        String result = client.createTrade().batchCreateOrder(parameters);

        System.out.println(result);
    }
}
