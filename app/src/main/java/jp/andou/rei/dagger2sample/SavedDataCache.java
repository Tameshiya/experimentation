package jp.andou.rei.dagger2sample;

//data package

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class SavedDataCache {

    private final SharedPreferences sharedPreferences;

    @Inject
    public SavedDataCache(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    boolean saveData(String data) {
        return sharedPreferences.edit()
                .putString("DATA", data)
                .commit();
    }

    boolean clearData() {
        return sharedPreferences.edit()
                .remove("DATA")
                .commit();
    }
}
