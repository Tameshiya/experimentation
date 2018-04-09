package jp.andou.rei.dagger2sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SecondActivity extends AppCompatActivity {

    private Unbinder unbinder;
    @BindView(R.id.data)
    TextView data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondview);
        ((App) getApplication()).getApplicationComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        data.setText(getIntent().getExtras().getString("DATA"));
    }


}
