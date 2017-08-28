package indi.zhuyst.retrofitlearn.client.model;

import indi.zhuyst.retrofitlearn.client.pojo.Msg;
import indi.zhuyst.retrofitlearn.client.pojo.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhuyst on 2017/7/6.
 */

public interface UserModel {
    @POST("user/login")
    Observable<Msg> login(@Body User user);

    @POST("user/insert")
    Observable<Msg> register(@Body User user);

    @PUT("user/{username}/update")
    Observable<Msg> updateInfo(@Path("username")String username,@Body User user);

    @GET("user/{username}/query")
    Observable<Msg<User>> getUser(@Path("username") String username);
}
