package jp.andou.rei.dagger2sample;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    private final Context applicationContext;

    public SharedPreferencesModule(Context context) {
        this.applicationContext = context;
    }

    @Provides
    public SharedPreferences getGeneralSharedPreferences() {
        return applicationContext.getSharedPreferences("RANDOM", Context.MODE_PRIVATE);
    }
}
