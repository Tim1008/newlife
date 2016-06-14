package vrtbin.com.newlife.utils;

import java.security.Key;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Desc  :
 * Date  : 16/6/13.
 * Author: tim.
 */
public class AESUtils {

  private static final String AES_TYPE = "AES/ECB/PKCS5Padding";
  private static final String AES = "AES";
  private static final String STRING_FOR_RANDOM =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public static String encryptWithECBAndBase64(String key, String origin) {
    byte[] encrypt;
    try {
      Key k = generateKey(key);

      Cipher cipher = Cipher.getInstance(AES_TYPE);
      cipher.init(Cipher.ENCRYPT_MODE, k);
      encrypt = cipher.doFinal(origin.getBytes());

    } catch (Exception e) {
      return null;
    }
    return Base64Utils.encode(encrypt);

  }

  public static String decryptWithECBAndBase64(String key, String target) {

    byte[] decrypt;

    try {
      Key k = generateKey(key);
      Cipher cipher = Cipher.getInstance(AES_TYPE);
      cipher.init(Cipher.DECRYPT_MODE, k);
      decrypt = cipher.doFinal(Base64Utils.decode(target));

    } catch (Exception e) {
      return null;
    }

    return new String(decrypt);

  }

  private static Key generateKey(String key) throws Exception {
    try {
      return new SecretKeySpec(key.getBytes(), AES);
    } catch (Exception e) {
      return null;
    }

  }

  public static String getRandomKey() {
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 16; i++) {
      int number = random.nextInt(62);
      sb.append(STRING_FOR_RANDOM.charAt(number));
    }
    return sb.toString();
  }

}
