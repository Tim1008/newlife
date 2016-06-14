package vrtbin.com.newlife.common.http;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Desc :
 * Date : 16/8/5.
 * Author: tim.
 */
public class ApiCreator {

  private static ApiCreator sInstance = new ApiCreator();

  private HashMap<String, WeakReference> mServices = new HashMap<>();

  private ApiCreator() {}

  public static ApiCreator getInstance() {
    if (sInstance == null) {
      synchronized (ApiCreator.class) {
        if (sInstance == null) {
          sInstance = new ApiCreator();
        }
      }
    }
    return sInstance;
  }

  public <T> T createApi(Class<T> clazz) {
    if (clazz == null) {
      return null;
    }
    T service;
    WeakReference reference = mServices.get(clazz.getName());
    if (null != reference && null != reference.get()) {
      try {
        service = (T) reference.get();
      } catch (Exception e) {
        e.printStackTrace();
        service = OKHttpWrapper.getInstance().getRetrofit().create(clazz);
        mServices.put(clazz.getName(), new WeakReference(service));
      }
    } else {
      service = OKHttpWrapper.getInstance().getRetrofit().create(clazz);
      mServices.put(clazz.getName(), new WeakReference(service));
    }
    return service;
  }
}
