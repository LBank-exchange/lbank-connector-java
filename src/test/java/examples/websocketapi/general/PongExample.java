package examples.websocketapi.general;

import com.lbank.open.connector.client.WebSocketApiClient;
import com.lbank.open.connector.client.impl.WebSocketApiClientImpl;
import examples.PrivateConfig;
import org.json.JSONObject;

import java.util.UUID;

public class PongExample {


    public static void main(String[] args) throws Exception {
        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.WS_API_URL);
        client.connect(((event) -> {
            System.out.println(event);
        }));


        JSONObject params = new JSONObject();
        params.put("action", "pong");
        params.put("pong", "81580abb-2fb3-4ffe-aa8e-ae4e62d461ba");


        client.general().pong(params);

//        client.close();

    }
}
