package jp.andou.rei.dagger2sample;

import android.view.View;

public interface MainScreenContract {

    interface MainView {
        void confirmInput(View v);
        void showError(String message);
        void startSecondActivity(String data);
    }

    interface MainScreenPresenter {
        void saveData(String data);
        boolean checkData(String data);
    }

}
