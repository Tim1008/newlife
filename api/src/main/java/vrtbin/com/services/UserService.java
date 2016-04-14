package vrtbin.com.services;

import retrofit2.http.POST;
import rx.Observable;
import vrtbin.com.bean.User;

/**
 * Desc  : 用户相关接口
 * Date  : 16-4-14.
 * Author: tim.
 */
public interface UserService {

    @POST("user/loginByPhone")
    Observable<User> loginByPhone();
}
