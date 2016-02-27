package com.expensetracking.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sandz on 02/27/16.
 */
public class APIUtils {
    Activity activity;

    public APIUtils(Activity activity) {
        this.activity = activity;
    }

    public String getAuthorizationToken() {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(activity.getApplicationContext());

        String auth_token = settings.getString("auth_token", null);
        return auth_token;
    }

    public String getHeaderAuthorizationToken() {
        return " Token " + getAuthorizationToken();
    }
}
