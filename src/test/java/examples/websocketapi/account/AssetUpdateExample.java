package examples.websocketapi.account;

import com.lbank.open.connector.client.WebSocketApiClient;
import com.lbank.open.connector.client.impl.WebSocketApiClientImpl;
import examples.PrivateConfig;
import org.json.JSONObject;

public class AssetUpdateExample {


    public static void main(String[] args) throws Exception {

        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.WS_API_URL);
        client.connect(((event) -> {
            System.out.println(event);
        }));


        JSONObject params = new JSONObject();
        params.put("action", "subscribe");
        params.put("subscribe", "assetUpdate");
        params.put("subscribeKey", "4027b6ac2ac1ef6f96748997b82e50cffacce45fd7ca105d1d075cbe38cc3baf");



        client.account().assetUpdate(params);

//        client.close();
    }
}
