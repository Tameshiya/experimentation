package jp.andou.rei.dagger2sample;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPreferencesModule.class, NetworkModule.class, ApplicationCacheModule.class})
public interface AppComponent {

    void inject(MainActivity view);

    void inject(SecondActivity view);

}
