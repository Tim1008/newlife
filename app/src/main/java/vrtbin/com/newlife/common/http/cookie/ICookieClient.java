package vrtbin.com.newlife.common.http.cookie;

import java.net.HttpCookie;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Desc : cookie业务接口
 * Date : 16/6/13.
 * Author: tim.
 */
public interface ICookieClient {
  String KEY_REQUEST_COOKIE = "Cookie";
  String KEY_RESPONSE_COOKIE = "Set-Cookie";

  /**
   * 通过CookieManage解析header头并cache
   */
  void saveCookie(URI uri, Map<String, List<String>> header);

  /**
   * 直接cache cookie信息
   */
  void saveCookie(URI uri, HttpCookie cookie);

  /**
   * @return host或domain匹配下的所有缓存cookie值（以“;“作为分隔符的键值对string）
   */
  String getCookieValue(URI uri);

  /**
   * @return host或domain匹配下的所有缓存httpCookie集
   */
  List<HttpCookie> getCookies(URI uri);

  /**
   * clear all cache
   */
  void clear();
}
