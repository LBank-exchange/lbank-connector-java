package examples.spot.wallet;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;
import javafx.scene.effect.Light;

public class SystemStatusExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.BASE_URL);

        String result = client.createWallet().systemStatus();

        System.out.println(result);
    }
}
