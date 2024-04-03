package examples.old.spot.market;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class TradesExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.BASE_URL);


        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "btc_usdt");
        parameters.put("size", 50);

        String result = client.createMarket().trades(parameters);

        System.out.println(result);
    }
}
