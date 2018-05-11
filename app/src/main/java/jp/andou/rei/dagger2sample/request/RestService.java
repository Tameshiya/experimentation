package jp.andou.rei.dagger2sample.request;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RestService {

    @GET("/")
    Observable<User> getUserInfo();

    @GET("/")
    Observable<User> getParametrizedUserInfo(
            @Query("userId") int userId,
            @Query("userName") String userName,
            @Query("someIds") List<Integer> someIds
    );

    @GET("/")
    Single<User> getSingleUserInfo();

    @GET("/")
    Call<User> oldSchoolRequest();

}
