package jp.andou.rei.dagger2sample.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectModel {

    @SerializedName("array")
    List<Integer> array;

    @SerializedName("phrase")
    String phrase;

    public List<Integer> getArray() {
        return array;
    }

    public String getPhrase() {
        return phrase;
    }
}
