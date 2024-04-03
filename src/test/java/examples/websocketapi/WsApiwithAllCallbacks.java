package examples.websocketapi;

import com.lbank.open.connector.client.WebSocketApiClient;
import com.lbank.open.connector.client.impl.WebSocketApiClientImpl;
import com.lbank.open.connector.client.utils.websocketcallback.*;
import examples.PrivateConfig;
import org.json.JSONObject;


public final class WsApiwithAllCallbacks {
    private WsApiwithAllCallbacks() {
    }

    private static WebSocketOpenCallback onOpenCallback;
    private static WebSocketMessageCallback onMessageCallback;
    private static WebSocketClosingCallback onClosingCallback;
    private static WebSocketClosedCallback onClosedCallback;
    private static WebSocketFailureCallback onFailureCallback;

    private static final int waitTime = 60000;

    public static void main(String[] args) throws InterruptedException {

        onOpenCallback = openEvent -> {
            System.out.println("Open Connection: " + openEvent.toString());
        };

        onMessageCallback = (message) -> {
            System.out.println("Connection Message:" + message);
        };

        onClosingCallback = (code, reason) -> {
            System.out.println("Closing Connection: code=" + code + ", reason=" + reason);
        };

        onClosedCallback = (code, reason) -> {
            System.out.println("Closed Connection: code=" + code + ", reason=" + reason);
        };

        onFailureCallback = (throwable, response) -> {
            System.out.println("Connection Failed: throwable=" + throwable.getMessage());
        };

        WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.WS_API_URL);
        client.connect(onOpenCallback, onMessageCallback, onClosingCallback, onClosedCallback, onFailureCallback);
        
        JSONObject params = new JSONObject();
        params.put("action", "ping");
//        client.general().ping(params);

        Thread.sleep(waitTime);
        client.close();
    }
}