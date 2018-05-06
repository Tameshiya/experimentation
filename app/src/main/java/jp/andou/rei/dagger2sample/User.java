package jp.andou.rei.dagger2sample;

import com.google.gson.annotations.SerializedName;

class User {
    /*
    "current_user_url": "https://api.github.com/user",
            "current_user_authorizations_html_url": "https://github.com/settings/connections/applications{/client_id}",
            "authorizations_url": "https://api.github.com/authorizations"*/
    @SerializedName("current_user_url")
    private String currentUserUrl;

    @SerializedName("current_user_authorizations_html_url")
    private String anotherUrl;

    @SerializedName("authorizations_url")
    private String authorizationUrl;

    public String getCurrentUserUrl() {
        return currentUserUrl;
    }

    public String getAnotherUrl() {
        return anotherUrl;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }
}
