package jp.andou.rei.dagger2sample;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import jp.andou.rei.dagger2sample.MainScreenContract.MainScreenPresenter;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;

public class MainPresenter implements MainScreenPresenter, Presenter<MainView> {

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
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    @Nullable
    public String saveData(String data) {
        //interactor.saveData(); for uniform access
        return checkData(data) && dataCache.saveData(data) ? data : null;
    }

    @Override
    public boolean checkData(String data) {
        return data.length() > 3;
    }
}
