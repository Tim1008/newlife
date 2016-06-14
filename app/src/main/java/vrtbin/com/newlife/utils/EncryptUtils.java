package vrtbin.com.newlife.utils;

import android.text.TextUtils;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class EncryptUtils {
  private static final String ALGORITHM = "RSA";
  private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1PADDING";

  public static String getEncryptPassWord(String pwd) {
    if (TextUtils.isEmpty(pwd)) {
      return null;
    }
    final String md5Pwd = MD5Utils.md5(pwd);// MD5加密
    if (TextUtils.isEmpty(md5Pwd)) {
      return null;
    }

    final String rsaPwd = encrypt(md5Pwd, getRSAPublicKey());
    if (TextUtils.isEmpty(rsaPwd)) {
      return null;
    }
    return rsaPwd;
  }

  public static String getRSAPublicKey() {
    return "rsa_public_key"; // 使用时替换成有效公钥
  }


  public static String encrypt(String content, String key) {
    try {
      PublicKey publicKey = getPublicKeyFromX509(ALGORITHM, key);
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, publicKey);
      byte plaintext[] = content.getBytes("UTF-8");
      byte[] output = cipher.doFinal(plaintext);
      return Base64Utils.encode(output);
    } catch (Exception e) {
      return null;
    }
  }

  private static PublicKey getPublicKeyFromX509(String algorithm,
      String bysKey) throws Exception {
    byte[] decodedKey = Base64Utils.decode(bysKey);
    X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);
    KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
    return keyFactory.generatePublic(x509);
  }

}
