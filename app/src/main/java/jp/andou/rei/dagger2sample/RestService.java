package jp.andou.rei.dagger2sample;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

interface RestService {

    @GET("/")
    Single<User> getUserInfo();

    @GET("/")
    Call<User> getRetrofitCallUserInfo();

}
