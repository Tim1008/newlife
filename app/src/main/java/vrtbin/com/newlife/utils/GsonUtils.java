package vrtbin.com.newlife.utils;

import com.google.gson.Gson;

/**
 * Desc  :
 * Date  : 16/6/14.
 * Author: tim.
 */
public class GsonUtils {
  private static Gson sGson = new Gson();

  public static Gson getGson() {
    return sGson;
  }
}
