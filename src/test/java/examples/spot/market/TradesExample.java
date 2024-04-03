package examples.spot.market;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class TradesExample {

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "ups_usdt");
        parameters.put("size", "100");
//        parameters.put("time", "1644386190138");

        SpotClient spotClient = SpotClient.getInstance(PrivateConfig.BASE_URL);
        String result = spotClient.createMarket().trades(parameters);
        System.out.println(result);
    }
}
