package examples.spot.wallet;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import examples.PrivateConfig;

import java.util.HashMap;
import java.util.Map;

public class WithdrawExample {


    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("address", "18502992190");
        parameters.put("coin", "usdt");
        parameters.put("amount", "1");
        parameters.put("type", "1");


//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("address", "0x83f3cbee19b7cd44e369927016d7c02ec01ef5b9");
//        parameters.put("networkName", "erc20");
//        parameters.put("coin", "usdt");
//        parameters.put("amount", "1");
//        parameters.put("mark", "test");
//        parameters.put("fee", "0.2");
//        parameters.put("name", "1");
//        parameters.put("withdrawOrderId", "214312311");
//        parameters.put("type", "2");


        String result = client.createWallet().withdraw(parameters);

        System.out.println(result);
    }
}
