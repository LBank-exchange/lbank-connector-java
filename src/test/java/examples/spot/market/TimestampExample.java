package examples.spot.market;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

public class TimestampExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.BASE_URL);
        String result = client.createMarket().timestamp();
        System.out.println(result);
    }
}
