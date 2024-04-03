package examples.websocketapi.market;

import com.lbank.open.connector.client.WebSocketApiClient;
import com.lbank.open.connector.client.impl.WebSocketApiClientImpl;
import examples.PrivateConfig;
import org.json.JSONObject;

public class TradeExample {

    public static void main(String[] args) throws Exception {
        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.WS_API_URL);
        client.connect(((event) -> {
            System.out.println(event);
        }));


        JSONObject params = new JSONObject();
        params.put("action", "subscribe");
        params.put("subscribe", "trade");
        params.put("pair", "eth_btc");


        client.market().trade(params);

//        client.close();
    }
}
