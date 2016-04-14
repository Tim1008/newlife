package vrtbin.com.newlife;

import android.app.Application;

import vrtbin.com.ApiClient;

/**
 * Desc  :
 * Date  : 16-4-14.
 * Author: tim.
 */
public class LifeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initApiClient();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initApiClient(){
        ApiClient.getClient().initService();
    }
}
