package jp.andou.rei.dagger2sample;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setView(this);
    }

    @Override
    @OnClick(R.id.textView)
    public void confirmInput(View v) {
        presenter.saveData(((TextView) v).getText().toString());
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void startSecondActivity() {

    }


}
