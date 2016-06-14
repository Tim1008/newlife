package vrtbin.com.newlife.common.storage;

import android.content.Context;
import android.content.SharedPreferences;

import vrtbin.com.newlife.config.EnvironmentType;
import vrtbin.com.newlife.config.GlobalConfig;


/**
 * Desc : SharedPreferences 存储类
 * Date : 16/6/13.
 * Author: tim.
 */
public class LifeSharedPreferences {
  private static final String PREFS_PATH = "prefs";
  private static SharedPreferences sPrefs;

  private static final String ACCOUNT_DATA = "account_data";
  private static final String ENVIRONMENT_TYPE = "environment_type";
  private static final String ENVIRONMENT_BASE_API_URL = "environment_base_api_url";

  private static final String SECURE_K2 = "nothing_to_tell";


  public static void clear() {
    SharedPreferences.Editor editor = getPrefs().edit();
    editor.clear();
    editor.apply();
  }

  private static SharedPreferences getPrefs() {
    if (sPrefs == null) {
      sPrefs = GlobalConfig.getAppContext().getSharedPreferences(PREFS_PATH, Context.MODE_PRIVATE);
    }
    return sPrefs;
  }


  public static void preLoadPrefs() {
    getPrefs();
  }

  public static String getBpAccountModel() {
    return getPrefs().getString(ACCOUNT_DATA, "");
  }

  public static void setBpAccountModel(String value) {
    SharedPreferences.Editor editor = getPrefs().edit();
    editor.putString(ACCOUNT_DATA, value);
    editor.apply();
  }

  public static String getSecureK2() {
    return getPrefs().getString(SECURE_K2, "");
  }

  public static void setSecureK2(String k2) {
    SharedPreferences.Editor editor = getPrefs().edit();
    editor.putString(SECURE_K2, k2);
    editor.apply();
  }

  public static int getEnvironmentType() {
    return getPrefs().getInt(ENVIRONMENT_TYPE, EnvironmentType.Product.ordinal());
  }

  public static void setEnvironmentType(int value) {
    SharedPreferences.Editor editor = getPrefs().edit();
    editor.putInt(ENVIRONMENT_TYPE, value);
    editor.apply();
  }

  public static String getBaseApiUrl() {
    return getPrefs().getString(ENVIRONMENT_BASE_API_URL,
        EnvironmentType.Product.getApiBaseUrl());
  }

  public static void setBaseApiUrl(String url) {
    SharedPreferences.Editor editor = getPrefs().edit();
    editor.putString(ENVIRONMENT_BASE_API_URL, url);
    editor.apply();
  }
}
