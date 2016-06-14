package vrtbin.com.newlife.config;

import android.content.Context;

import vrtbin.com.newlife.BuildConfig;

/**
 * Desc  :
 * Date  : 16/6/13.
 * Author: tim.
 */
public class AppEnvironment {
  private static EnvironmentStub sEnvironmentStub;

  public static synchronized void init(EnvironmentStub stub) {
    if (stub != null && stub.getEnvironment() != null) {
      sEnvironmentStub = stub;
    }
  }

  public static EnvironmentType getEnvironment() {
    return sEnvironmentStub.getEnvironment();
  }

  public static EnvironmentStub getEnvironmentStub() {
    return sEnvironmentStub;
  }

  public interface EnvironmentStub {
    void setEnvironmentOrdinal(int ordinal);

    EnvironmentType getEnvironment();
  }

  public static int getVersionCode() {
    return BuildConfig.VERSION_CODE;
  }

  public static String getUserAgent(Context context) {
    return "Life;" + getVersionName() + ";Android;" + "udid";
  }

  public static String getVersionName() {
    return BuildConfig.VERSION_NAME;
  }

  public static boolean isDebugable(){
    return BuildConfig.DEBUG;
  }
}
