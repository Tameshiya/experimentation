package jp.andou.rei.dagger2sample;

import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import jp.andou.rei.dagger2sample.MainScreenContract.MainScreenPresenter;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;
import retrofit2.Response;

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
//            view.startSecondActivity(data);
            Request<User> request = service.getGithubGreeting();
            request.getResponse()
                    .subscribe((user) -> view.startSecondActivity(user.getCurrentUserUrl()));
            request.getState()
                    .subscribe(state -> Log.d("STATE", state.name()));
            request.execute();

        }
    }

    @Override
    public boolean checkData(String data) {
        return data.length() > 3;
    }
}
