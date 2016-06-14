package vrtbin.com.newlife.common.http;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import vrtbin.com.newlife.common.http.config.OkHttpConfigImpl;
import vrtbin.com.newlife.utils.CollectionUtils;

/**
 * Desc  :
 * Date  : 16/6/13.
 * Author: tim.
 */
public class OKHttpWrapper {
  private static OKHttpWrapper sInstance;
  private OkHttpConfigImpl mConfig;
  private OkHttpClient mOkHttpClient;
  private Retrofit mRetrofit;
  private Retrofit.Builder mBuilder;

  private OKHttpWrapper() {
    init();
  }

  private void init() {
    initConfig();
    initOkHttpClient();
    initRetrofit();
  }

  private void initConfig() {
    mConfig = new OkHttpConfigImpl();
  }

  private void initOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    if (mConfig.getCache() != null) {
      builder.cache(mConfig.getCache());
    }
    if (!CollectionUtils.isEmpty(mConfig.getInterceptors())) {
      for (Interceptor i : mConfig.getInterceptors()) {
        builder.addInterceptor(i);
      }
    }

    mOkHttpClient = builder.build();
  }

  /**
   * retrofit默认连接配置，连接超时15秒,读取超时20秒,没有写入超时
   */
  private void initRetrofit() {
    mBuilder = new Retrofit.Builder();
    mBuilder.callFactory(mOkHttpClient)
        .baseUrl(mConfig.getBaseUrl());
    if (!CollectionUtils.isEmpty(mConfig.getConverters())) {
      for (Converter.Factory c : mConfig.getConverters()) {
        mBuilder.addConverterFactory(c);
      }
    }
    if (!CollectionUtils.isEmpty(mConfig.getCallAdapters())) {
      for (CallAdapter.Factory adapter : mConfig.getCallAdapters()) {
        mBuilder.addCallAdapterFactory(adapter);
      }
    }
    mRetrofit = mBuilder.build();
  }

  public static OKHttpWrapper getInstance() {
    if (sInstance == null) {
      synchronized (OKHttpWrapper.class) {
        if (sInstance == null) {
          sInstance = new OKHttpWrapper();
        }
      }
    }
    return sInstance;
  }

  public OkHttpClient getOkHttpClient() {
    return mOkHttpClient;
  }

  public Retrofit getRetrofit() {
    return mRetrofit;
  }

  public boolean clearCache() {
    try {
      mConfig.getCache().delete();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public void updateBaseUrl(String url) {
    if (!TextUtils.isEmpty(url)) {
      mBuilder = mBuilder.baseUrl(url);
      mRetrofit = mBuilder.build();
    }
  }
}
