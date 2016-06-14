package vrtbin.com.newlife.utils;

import android.util.Base64;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class Base64Utils {

  public static String encode(byte[] encrypt) {
    byte[] result = Base64.encode(encrypt, Base64.DEFAULT);
    return new String(result);
  }

  public static byte[] decode(byte[] decrypt) {
    return Base64.decode(decrypt, Base64.DEFAULT);
  }

  public static byte[] decode(String decrypt) {
    return Base64.decode(decrypt, Base64.DEFAULT);
  }

}
