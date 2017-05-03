package id.buruhsd.nicepay.Model;

/**
 * Created by buruhsd on 02/05/17.
 */

public class NicePay {
    String cardExpYymm;
    String cardToken;

    public NicePay(String cardToken) {
        this.cardToken = cardToken;
    }

    public NicePay(){

    }
    public String getCardToken() {
        return cardToken;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }
}
