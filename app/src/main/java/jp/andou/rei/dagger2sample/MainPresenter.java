package jp.andou.rei.dagger2sample;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import jp.andou.rei.dagger2sample.MainScreenContract.MainScreenPresenter;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;

public class MainPresenter implements MainScreenPresenter, Presenter<MainView> {

    @Nullable
    private MainView view;
    /**
     * Move to interactor
     */
    @Deprecated
    private SavedDataCache dataCache;

    @Inject
    public MainPresenter(MainInteractor interactor, SavedDataCache dataCache) {
        this.view = view;
        interactor.test();
        this.dataCache = dataCache;
    }

    @Override
    public void setView(@Nullable MainView view) {
        this.view = view;
    }

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
            view.startSecondActivity(data);
        }
    }

    @Override
    public boolean checkData(String data) {
        return data.length() > 3;
    }
}
