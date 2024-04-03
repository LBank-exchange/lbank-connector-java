package examples.spot.wallet;

import com.lbank.open.connector.client.SpotClient;
import com.lbank.open.connector.client.impl.SpotClientImpl;
import com.lbank.open.connector.client.utils.signaturegenerator.RsaSignatureGenerator;
import examples.PrivateConfig;


public class UserInfoExample {

    public static void main(String[] args) {
        SpotClient client = SpotClient.getInstance(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY, PrivateConfig.BASE_URL, PrivateConfig.SIGN_METHOD);

        String result = client.createWallet().userInfo(null);

        System.out.println(result);


    }
}
