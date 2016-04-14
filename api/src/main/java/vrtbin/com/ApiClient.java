package vrtbin.com;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vrtbin.com.services.UserService;

/**
 * Desc  :
 * Date  : 16-4-14.
 * Author: tim.
 */
public class ApiClient {

    public UserService userService;

    private static ApiClient apiClient;
    private boolean isInit;

    private ApiClient() {
    }

    public static ApiClient getClient(){
        if (null == apiClient) {
            synchronized (ApiClient.class) {
                if (null == apiClient) {
                    apiClient = new ApiClient();
                }
            }
        }

        return apiClient;
    }


    public void initService(){
        if (!isInit) {
            isInit = true;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.vrtbin.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            userService = retrofit.create(UserService.class);
        }
    }
}
