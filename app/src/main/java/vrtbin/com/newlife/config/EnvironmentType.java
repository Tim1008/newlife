package vrtbin.com.newlife.config;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public enum EnvironmentType {

  Product {
    @Override
    public String getApiBaseUrl() {
      return PRODUCT;
    }
  },
  Test {
    @Override
    public String getApiBaseUrl() {
      return TEST;
    }
  };

  private static final String TEST = "http://api.test.com";

  private static final String PRODUCT = "https://api.com";

  private static String sApiBaseUrl;

  public String getApiBaseUrl() {
    return sApiBaseUrl;
  };

  public static void setApiBaseUrl(String apiBaseUrl) {
    sApiBaseUrl = apiBaseUrl;
  }

}
