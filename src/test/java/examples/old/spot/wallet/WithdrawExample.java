package examples.old.spot.wallet;

import com.lbank.open.connector.client.old.SpotClient;
import com.lbank.open.connector.client.old.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WithdrawExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account", "18502992190");
        parameters.put("assetCode", "usdt");
        parameters.put("amount", "1");
        parameters.put("type", "1");

        String result = client.createWallet().withdraw(parameters);

        System.out.println(result);
    }
}
