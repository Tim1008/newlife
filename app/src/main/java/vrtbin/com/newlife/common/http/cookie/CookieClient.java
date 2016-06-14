package vrtbin.com.newlife.common.http.cookie;



import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vrtbin.com.newlife.utils.CollectionUtils;

/**
 * Desc  :
 * Date  : 16/6/13.
 * Author: tim.
 */
public class CookieClient implements ICookieClient {
  private static ICookieClient sInstance;
  private CookieManager mCookieManager;
  private CookieStoreWrap mCookieStore;

  private CookieClient() {
    mCookieStore = new CookieStoreWrap();
    mCookieManager = new CookieManager(mCookieStore, CookiePolicy.ACCEPT_ALL);
  }

  public static synchronized ICookieClient getInstance() {
    if (sInstance == null) {
      sInstance = new CookieClient();
    }
    return sInstance;
  }

  @Override
  public void saveCookie(URI uri, Map<String, List<String>> header) {
    if (uri == null || header == null) {
      return;
    }
    try {
      mCookieManager.put(uri, header);
    } catch (IOException e) {
      // do nothing
    }
  }

  @Override
  public void saveCookie(URI uri, HttpCookie cookie) {
    if (uri == null || cookie == null) {
      return;
    }
    mCookieStore.add(uri, cookie);
  }

  @Override
  public String getCookieValue(URI uri) {
    if (uri == null) {
      return null;
    }
    Map<String, List<String>> cookieHeader = null;

    try {
      cookieHeader = mCookieManager.get(uri, new HashMap<String, List<String>>());
    } catch (IOException e) {
      // do nothing
    }

    if (CollectionUtils.isEmpty(cookieHeader)
            || CollectionUtils.isEmpty(cookieHeader.get(KEY_REQUEST_COOKIE))) {
      return null;
    }

    return cookieHeader.get(KEY_REQUEST_COOKIE).get(0);
  }

  @Override
  public List<HttpCookie> getCookies(URI uri) {
    return mCookieStore.get(uri);
  }

  @Override
  public void clear() {
    mCookieStore.removeAll();
  }
}
