# LBank Public API connector Java

This is a lightweight library that works as a connector to the [LBank public API](https://www.lbank.com/en-US/docs/index.html).

It supports the following APIs:
- Spot Rest API;
- Spot WebSocket API;

Additionally, it includes test cases and examples.

## Documentation
[https://www.lbank.com/en-US/docs/index.html](https://www.lbank.com/en-US/docs/index.html)

## Examples
The examples are located under **src/test/java/examples**. Before running any of it, `PrivateConfig.java` must be set up correctly with `API_KEY` and` SECRET_KEY` and `SIGN_METHOD` .

Note that this `PrivateConfig.java` is only used for examples, you should have your own configuration file when using the library.

### REST APIs

#### Market Endpoint: Exchange Information
```java
SpotClient client = SpotClient.getInstance(PrivateConfig.BASE_URL);
Map<String, Object> parameters = new LinkedHashMap<>();
String result = client.createMarket().bookTicker(parameters);
```

#### Trade Endpoint: Testing a new order
```java
SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

Map<String, Object> parameters = new HashMap<>();
parameters.put("symbol", "eth_btc");
parameters.put("type", "buy");
parameters.put("price", "100");
parameters.put("amount", "10");

String result = client.createTrade().createOrderTest(parameters);
```

### WebSocket API
```java
WebSocketApiClient client = new WebSocketApiClientImpl(PrivateConfig.WS_API_URL);

client.connect(((event) -> {
    System.out.println(event);
}));


JSONObject params = new JSONObject();
params.put("action", "subscribe");
params.put("subscribe", "depth");
params.put("depth", "100");
params.put("pair", "eth_btc");


client.market().depth(params);

//process done
        ....

client.close();

```

If `requestId` is empty (`""`), `null` or not sent, this library will generate a `UUID` string for it.

Different types of WebSocket callbacks are available. Please refer to the `src/test/java/examples/websocketapi/WsApiwithAllCallbacks.java` example file to explore their usage.

## Features
### Optional parameters
Parameters can be set in any implementation of `Map<String, Object>` interface, where `String` represents the parameter name and `Object` the parameter value. These parameters should have the same naming as in the API doc."

```java
Map<String,Object> parameters = new LinkedHashMap<String,Object>();

parameters.put("symbol", "eth_btc");
parameters.put("type", "buy");
parameters.put("price", "100");
parameters.put("amount", "10");
```


### Types of Signature Generator
When creating `SpotClient` you use one of the following types of Signature to create signatures (for SIGNED endpoints) based on your security preference:

- `HmacSHA256` - Use of API Key and Secret Key.
```java
SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, "HmacSHA256");
```

- `RSA` - Use of API Key and RSA algorithm keys.
```java
SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, "RSA");
```

## Errors

There are 3 types of error which may be thrown by this library.

- `LbankConnectorException`
  - This is thrown when there is a validation error for parameters.For instance, mandatory parameter not sent. This error will be thrown before the request is sent to the server.
- `LbankClientException`
  - This is thrown when server returns `4XX`, it's an issue from client side.
  - The error consists of these 3 objects which will help in debugging the error:
    - `httpStatusCode` - HTTP status code
    - `errorCode` - API Server's error code, e.g. `-10000`
- `LbankServerException`
  - This is thrown when server returns `5XX`, it's an issue from server side.
```java
try {
      String result = client.createTrade().createOrderTest(parameters);
      logger.info(result);
    } catch (LbankConnectorException e) {
      logger.error("fullErrMessage: {}", e.getMessage(), e);
    } catch (LbankClientException e) {
      logger.error("fullErrMessage: {} \nerrMessage: {} \nerrCode: {} \nHTTPStatusCode: {}",
      e.getMessage(), e.getErrMsg(), e.getErrorCode(), e.getHttpStatusCode(), e);
    }
```

## Test Cases
`mvn clean test`


