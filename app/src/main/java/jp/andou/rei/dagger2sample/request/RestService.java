package jp.andou.rei.dagger2sample.request;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

interface RestService {

    @GET("/")
    Observable<User> getUserInfo();

    @GET("/")
    Single<User> getSingleUserInfo();

    @GET("/")
    Call<User> oldSchoolRequest();

}
