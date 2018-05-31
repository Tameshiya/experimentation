package jp.andou.rei.dagger2sample.request;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import jp.andou.rei.dagger2sample.CustomerInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface RestService {

    @GET("optional")
    Single<ObjectModel> getOptional();

    @GET("/")
    Observable<User> getParametrizedUserInfo(
            @Query("userId") int userId,
            @Query("userName") String userName,
            @Query("someIds") List<Integer> someIds
    );

    @GET("picking/issue/processQueue")
    Single<List<CustomerInfo>> getSingleUserInfo();

    @GET("/")
    Call<User> oldSchoolRequest();

}
