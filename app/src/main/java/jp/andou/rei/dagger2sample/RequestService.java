package jp.andou.rei.dagger2sample;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class RequestService {

    private final Retrofit retrofit;

    @Inject
    public RequestService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Request<User> getGithubGreeting() {
        return new Request<>(
                retrofit.create(RestService.class)
                        .getUserInfo()

        );
    }

    public Call<User> getGitHubGreetingOldSchool(){
        return retrofit.create(RestService.class)
                .getRetrofitCallUserInfo();
    }
}
