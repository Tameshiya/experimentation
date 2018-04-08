package jp.andou.rei.dagger2sample;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class ApplicationCacheModule {

    @Singleton
    @Provides
    public SavedDataCache getSharedPreferenceCache(SharedPreferences sharedPreferences) {
        return new SavedDataCache(sharedPreferences);
    }

}
