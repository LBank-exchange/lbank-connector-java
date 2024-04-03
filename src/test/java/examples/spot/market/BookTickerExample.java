package examples.spot.market;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class BookTickerExample {

    public static void main(String[] args) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "ups_usdt");

        SpotClient spotClient = SpotClient.getInstance(PrivateConfig.BASE_URL);
        String result = spotClient.createMarket().bookTicker(parameters);
        System.out.println(result);
    }
}
