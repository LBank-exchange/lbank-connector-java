package examples.old.spot.wallet;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.Map;

public class WithdrawsExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("status", "0");

        String result = client.createWallet().withdraws(parameters);

        System.out.println(result);
    }
}
