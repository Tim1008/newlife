package vrtbin.com.newlife.common.secure;

import android.text.TextUtils;

import vrtbin.com.newlife.common.storage.LifeSharedPreferences;
import vrtbin.com.newlife.config.AppEnvironment;
import vrtbin.com.newlife.utils.AESUtils;
import vrtbin.com.newlife.utils.EncryptUtils;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class SecureManager {

  public static final String PUBLIC_KEY_TEST =
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3//sR2tXw0wrC2DySx8vNGlqt" +
          "3Y7ldU9+LBLI6e1KS5lfc5jlTGF7KBTSkCHBM3ouEHWqp1ZJ85iJe59aF5gIB2kl" +
          "Bd6h4wrbbHA2XE1sq21ykja/Gqx7/IRia3zQfxGv/qEkyGOx+XALVoOlZqDwh76o" +
          "2n1vP1D+tD3amHsK7QIDAQAB";
  public static final String PUBLIC_KEY_PRODUCT =
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+OQdcyHdWMlDRF1O+eXtdoiX6" +
          "NAJQ//zxTJGxryxG5M1xExHOIntuHL2ECqJ9Bu6kiiAlpO2yEFFnCmFJdIM63Nmd" +
          "ftvvoYjGuOBZu7VqTP5SuEx0p1+8Q6kS1qZVaZ4siA0QlfFl+waXfGBennwZ0hou" +
          "SrXvlpBHCfxOWExIbQIDAQAB";

  public static String getAesKey() {
    return AESUtils.getRandomKey();
  }

  public static String encryptAesKey(String aesKey) {
    String aesKeyProxy = EncryptUtils.encrypt(aesKey, getPublicKey());
    return aesKeyProxy;
  }

  public static String handleK2(String aesKeyProxy, String aesKey) {
    String k2 = AESUtils.decryptWithECBAndBase64(aesKey, aesKeyProxy);
    if (!TextUtils.isEmpty(k2)) {
      LifeSharedPreferences.setSecureK2(k2);
    }
    return k2;
  }

  public static String getK2() {
    return LifeSharedPreferences.getSecureK2();
  }

  public static void clear() {
    LifeSharedPreferences.setSecureK2("");
  }

  public static String getPublicKey() {
    switch (AppEnvironment.getEnvironment()) {
      case Product:
        return PUBLIC_KEY_PRODUCT;
      case Test:
        return PUBLIC_KEY_TEST;
      default:
        return PUBLIC_KEY_TEST;
    }
  }
}
