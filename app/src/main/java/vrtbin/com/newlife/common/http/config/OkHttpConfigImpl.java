package vrtbin.com.newlife.common.http.config;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.Interceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vrtbin.com.newlife.common.http.interceptor.CookieInterceptor;
import vrtbin.com.newlife.common.http.interceptor.ParamsInterceptor;
import vrtbin.com.newlife.common.http.interceptor.SignInterceptor;
import vrtbin.com.newlife.config.AppEnvironment;
import vrtbin.com.newlife.config.GlobalConfig;

/**
 * Desc :
 * Date : 16/6/13.
 * Author: tim.
 */
public class OkHttpConfigImpl implements IOkHttpConfig {
  private List<Interceptor> mInterceptors;
  private Cache mCache;
  private List<Converter.Factory> mConverterFactories;
  private List<CallAdapter.Factory> mAdapterFactories;
  private static final String CACHE_PATH = "/api/okhttp";
  private static final int CACHE_SIZE = 5 * 1024 * 1024;

  public OkHttpConfigImpl() {
    initInterceptors();
    initCache();
    initConverters();
    initCallAdapters();
  }

  private void initInterceptors() {
    mInterceptors = new ArrayList<>();
    mInterceptors.add(new ParamsInterceptor());
//    mInterceptors.add(new CacheInterceptor());
    mInterceptors.add(new CookieInterceptor());
    mInterceptors.add(new SignInterceptor()); // 此interceptor需要放最后！！！
  }

  private void initCache() {
    String parentPath = getCacheParentPath();
    if (parentPath != null) {
      File f = new File(parentPath.concat(CACHE_PATH));
      if (f.exists() || f.mkdirs()) {
        mCache = new Cache(f, CACHE_SIZE);
      }
    }
  }

  private void initConverters() {
    mConverterFactories = new ArrayList<>();
    mConverterFactories.add(GsonConverterFactory.create());
  }

  private void initCallAdapters(){
    mAdapterFactories = new ArrayList<>();
    mAdapterFactories.add(RxJavaCallAdapterFactory.create());
  }

  @Override
  public List<Interceptor> getInterceptors() {
    return mInterceptors;
  }

  @Override
  public Cache getCache() {
    return mCache;
  }

  @Override
  public String getBaseUrl() {
    return AppEnvironment.getEnvironment().getApiBaseUrl();
  }

  @Override
  public List<Converter.Factory> getConverters() {
    return mConverterFactories;
  }

  @Override
  public List<CallAdapter.Factory> getCallAdapters() {
    return mAdapterFactories;
  }

  @Override
  public SSLSocketFactory getSSlSocketFactory() {
    return null; // TODO:
  }

  @Override
  public HostnameVerifier getHostnameVerifier() {
    return null; // TODO:
  }


  private String getCacheParentPath() {
    File file = GlobalConfig.getAppContext().getExternalFilesDir(null);
    if (file == null || !file.exists()) {
      file = GlobalConfig.getAppContext().getCacheDir();
    }
    String path = null;
    if (file != null) {
      path = file.getAbsolutePath();
    }
    return path;
  }
}
