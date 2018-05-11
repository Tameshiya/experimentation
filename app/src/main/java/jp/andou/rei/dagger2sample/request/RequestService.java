package jp.andou.rei.dagger2sample.request;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * このサービスクラスはファンクションの戻り値の型が実装の都合で○○Requestに限られていて
 * このクラス以外に{@link Request}をお使いください。
 */
public class RequestService implements RestService {

    private final RestService service;

    @Inject
    public RequestService(Retrofit retrofit) {
        service = retrofit.create(RestService.class);
    }

    @Override
    public ObservableRequest<User> getUserInfo() {
        return new ObservableRequest<>(service.getUserInfo());
    }

    @Override
    public Observable<User> getParametrizedUserInfo(int userId, String userName,
                                                           List<Integer> someIds) {
        return /*new ObservableRequest<>(*/service.getParametrizedUserInfo(userId, userName, someIds)
        /*)*/;
    }

    @Override
    public SingleRequest<User> getSingleUserInfo() {
        return new SingleRequest<>(service.getSingleUserInfo());
    }

    @Override
    public Call<User> oldSchoolRequest() {
        return service.oldSchoolRequest();
    }

}
