package examples.old.spot.market;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

public class WithdrawConfigsExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.BASE_URL);

        String result = client.createMarket().withdrawConfigs();

        System.out.println(result);
    }
}
