package examples.websocketapi.market;

import com.lbank.open.connector.client.WebSocketApiClient;
import com.lbank.open.connector.client.impl.WebSocketApiClientImpl;
import examples.PrivateConfig;
import org.json.JSONObject;

public class TickExample {

    public static void main(String[] args) throws Exception {
        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.WS_API_URL);
        client.connect(((event) -> {
            System.out.println(event);
        }));


        JSONObject params = new JSONObject();
        params.put("action", "subscribe");
        params.put("subscribe", "tick");
        params.put("pair", "eth_btc");


        client.market().tick(params);

//        client.close();
    }
}
