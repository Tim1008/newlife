package vrtbin.com.newlife.common.http.config;

import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * Desc : OkHttp ｜ retrofit configs
 * Date : 16/6/13.
 * Author: tim.
 */
public interface IOkHttpConfig {

  /**
   * @return interceptor for doing something on the request or response
   */
  List<Interceptor> getInterceptors();

  /**
   * @return cache for OkHttp lib
   */
  Cache getCache();

  /**
   * @return protocol + domain,like "http://vrtbin.com"
   */
  String getBaseUrl();

  /**
   * @return response data converter , json,xml, etc.
   */
  List<Converter.Factory> getConverters();

  /**
   * @return response call adapter, RxJavaCallAdapter.
   */
  List<CallAdapter.Factory> getCallAdapters();

  /**
   * @return ssl for https
   */
  SSLSocketFactory getSSlSocketFactory();

  /**
   * @return verifier for request
   */
  HostnameVerifier getHostnameVerifier();
}
