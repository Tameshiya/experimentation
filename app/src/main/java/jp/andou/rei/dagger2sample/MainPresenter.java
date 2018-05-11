package jp.andou.rei.dagger2sample;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import jp.andou.rei.dagger2sample.MainScreenContract.MainScreenPresenter;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;
import jp.andou.rei.dagger2sample.request.RequestExecutor;
import jp.andou.rei.dagger2sample.request.RequestService;

public class MainPresenter implements MainScreenPresenter {

    private final RequestService service;
    @Nullable
    private MainView view;
    /**
     * Move to interactor
     */
    @Deprecated
    private SavedDataCache dataCache;

    @Inject
    public MainPresenter(MainInteractor interactor, SavedDataCache dataCache, RequestService service) {
        this.view = view;
        interactor.test();
        this.dataCache = dataCache;
        this.service = service;
    }

    @Override
    public void setView(@Nullable MainView view) {
        this.view = view;
    }

    /**
     * todo 二つの要請方法のかける時間を掛かること
     * @param data
     */
    @Override
    public void saveData(String data) {
        //interactor.saveData(); for uniform access
        if (!checkData(data) && view != null) {
            view.showError("Введите текст больше 3х символов");
            return;
        }
        if (!dataCache.saveData(data) && view != null) {
            view.showError("Произошла ошибка при сохранении данных");
            return;
        }
        if (view != null) {
            /*Request<User> request = service.getSingleUserInfo();
            request.getResponse()
                    .subscribe((user) -> view.startSecondActivity(user.getCurrentUserUrl()));
            request.getState()
                    .subscribe(state -> Log.d("STATE", state.name()));
            request.execute();*/
            new RequestExecutor().execute(
                    1,
                    (i) -> service.getUserInfo(),
                    user -> view.startSecondActivity(user.getCurrentUserUrl())
            );
            /*service.oldSchoolRequest().enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    view.startSecondActivity(response.body().getCurrentUserUrl());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });*/
//            view.startSecondActivity(data);

        }
    }

    @Override
    public boolean checkData(String data) {
        return data.length() > 3;
    }
}
