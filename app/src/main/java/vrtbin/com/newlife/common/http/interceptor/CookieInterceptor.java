package vrtbin.com.newlife.common.http.interceptor;


import java.io.IOException;
import java.net.URI;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import vrtbin.com.newlife.common.http.cookie.CookieClient;
import vrtbin.com.newlife.common.http.cookie.ICookieClient;

/**
 * Desc : http请求的cookie获取和植入
 * Date : 16/6/13.
 * Author: tim.
 */
public class CookieInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    URI uri = request.url().uri(); // save 和 get对应uri一致
    String cookieValue = CookieClient.getInstance().getCookieValue(uri);
    if (cookieValue != null) {
      request = request
          .newBuilder()
          .addHeader(ICookieClient.KEY_REQUEST_COOKIE, cookieValue)
          .build();
    }

    Response response = chain.proceed(request);
    CookieClient.getInstance().saveCookie(uri, response.headers().toMultimap());
    return response;
  }
}
