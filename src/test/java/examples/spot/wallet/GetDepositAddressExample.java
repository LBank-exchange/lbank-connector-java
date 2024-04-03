package examples.spot.wallet;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.Map;

public class GetDepositAddressExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("coin", "usdt");


        String result = client.createWallet().getDepositAddress(parameters);

        System.out.println(result);
    }
}
