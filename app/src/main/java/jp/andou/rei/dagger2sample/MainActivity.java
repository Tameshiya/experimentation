package jp.andou.rei.dagger2sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.andou.rei.dagger2sample.MainScreenContract.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    MainPresenter presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getApplicationComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        presenter.setView(this);
    }

    @Override
    @OnClick(R.id.button)
    public void confirmInput(View v) {
        presenter.saveData(((TextView) v).getText().toString());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void startSecondActivity(String data) {
        //parcelableもおっけー
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("DATA", data);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.setView(null);
        unbinder.unbind();
    }
}
