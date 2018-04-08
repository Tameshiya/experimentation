package jp.andou.rei.dagger2sample;

import android.app.Application;

import javax.inject.Singleton;

public class App extends Application {

    private AppComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationComponent = createApplicationComponent();
    }

    public AppComponent createApplicationComponent() {
        return DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .applicationCacheModule(new ApplicationCacheModule())
                .build();
    }

    @Singleton
    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
