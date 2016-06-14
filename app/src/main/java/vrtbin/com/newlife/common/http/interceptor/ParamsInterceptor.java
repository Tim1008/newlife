package vrtbin.com.newlife.common.http.interceptor;

import com.feifan.bp.BuildConfig;
import com.feifan.bp.business.account.model.BpAccountModel;
import com.feifan.bp.business.account.wrapper.BpAccountManager;
import com.wanda.base.config.GlobalConfig;
import com.wanda.udid.UDIDUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Desc : 请求公共参数设置
 * Date : 16/6/13.
 * Author: tim.
 */
public class ParamsInterceptor implements Interceptor {

  private final static String KEY_APP_TYPE = "appType";
  private final static String KEY_CLIENT_TYPE = "clientType";
  private final static String KEY_VERSION = "version";
  private final static String KEY_UID = "uid";
  private final static String KEY_USER_ID = "userId";
  private final static String KEY_AG_ID = "agid";
  private final static String KEY_LOGIN_TOKEN = "loginToken";
  private final static String VALUE_APP_TYPE = "bpMobile";
  private final static String VALUE_CLIENT_TYPE = "Android";
  private final static String DEVICE_ID = "deviceId";
  private final static String KEY_UNI_SOURCE = "_uni_source";
  private final static String VALUE_UNI_SOURCE = "2.1";


  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    HttpUrl.Builder urlBuilder = request.url().newBuilder();
    urlBuilder.addQueryParameter(KEY_APP_TYPE, VALUE_APP_TYPE);
    urlBuilder.addQueryParameter(KEY_CLIENT_TYPE, VALUE_CLIENT_TYPE);
    urlBuilder.addQueryParameter(KEY_VERSION, String.valueOf(BuildConfig.VERSION_CODE));
    urlBuilder.addQueryParameter(DEVICE_ID, UDIDUtil.getUDID(GlobalConfig.getAppContext()));
    urlBuilder.addQueryParameter(KEY_UNI_SOURCE, VALUE_UNI_SOURCE);
    BpAccountModel model = BpAccountManager.getInstance().getAccountData();
    if (model != null) {
      urlBuilder.addQueryParameter(KEY_UID, String.valueOf(model.getUid()));
      urlBuilder.addQueryParameter(KEY_USER_ID, String.valueOf(model.getUid()));
      urlBuilder.addQueryParameter(KEY_AG_ID, String.valueOf(model.getAgId()));
      urlBuilder.addQueryParameter(KEY_LOGIN_TOKEN, model.getLoginToken());
    }
    HttpUrl httpUrl = urlBuilder.build();

    request = request.newBuilder().url(httpUrl).build();
    return chain.proceed(request);
  }
}
