package vrtbin.com.newlife.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class MD5Utils {

  public static final String ALGORITHM = "MD5";

  public static final String md5(final String src) {
    try {
      // Create MD5 Hash
      MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
      digest.update(src.getBytes());
      byte messageDigest[] = digest.digest();

      // Create Hex String
      StringBuffer hexString = new StringBuffer();
      for (int i = 0; i < messageDigest.length; i++) {
        String h = Integer.toHexString(0xFF & messageDigest[i]);
        while (h.length() < 2) {
          h = "0" + h;
        }
        hexString.append(h);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  public static boolean check(String content, String md5) {
    return md5(content).equals(md5);
  }

  // get md5 signed with key
  public static final String sign(String content, String key) {
    return md5(content + key);
  }

  // check md5 signed with key
  public static boolean check(String content, String key, String md5) {
    return md5(content + key).equals(md5);
  }

}
