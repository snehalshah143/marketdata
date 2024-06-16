package tech.algofinserve.infra;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.SessionExpiryHook;
import com.angelbroking.smartapi.models.TokenSet;
import com.angelbroking.smartapi.models.User;
import com.warrenstrange.googleauth.GoogleAuthenticator;

public final class AngelBrokerConnector {

  private AngelBrokerConnector() {}
  // This is going to be Per User
  //  private SmartConnect smartConnect = new SmartConnect();
  private static SmartConnect smartConnect = null;
  private static final GoogleAuthenticator gAuth = new GoogleAuthenticator();

  public static synchronized SmartConnect getSmartConnectSession(AngelApiKey angelApiKey) {
    if (smartConnect == null) {
      smartConnect = new SmartConnect();
    }
    smartConnect.setApiKey(angelApiKey.historicalApiKey);

    smartConnect.setSessionExpiryHook(
        new SessionExpiryHook() {
          @Override
          public void sessionExpired() {
            System.out.println("session expired");
          }
        });
    String totp = getTotp();
    if (smartConnect == null) {
      getSmartConnectSession(angelApiKey);
    }
    generateSession(smartConnect, AngelApiKey.clientID, AngelApiKey.passowrd, totp);
    return smartConnect;
  }

  private static String getTotp() {
    int code = gAuth.getTotpPassword(AngelApiKey.oneTimetotpKey);
    return String.valueOf(code);
  }

  private void regenerateToken(SmartConnect smartConnect, User user) {
    TokenSet tokenSet =
        smartConnect.renewAccessToken(user.getAccessToken(), user.getRefreshToken());
    smartConnect.setAccessToken(tokenSet.getAccessToken());
  }

  private static void generateSession(
      SmartConnect smartConnect, String clientID, String password, String topt) {
    if (smartConnect != null) {
      User user = smartConnect.generateSession(clientID, password, topt);
      smartConnect.setAccessToken(user.getAccessToken());
      smartConnect.setUserId(user.getUserId());
    }
  }
}
