package com.example.sampletest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sampletest.application.SampleApplication;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class SessionManager {

    private static final String KEY_JSON_DATA = "json_data";
    private static final String KEY_USER_LOGGED_IN = "loggedIn_user";
    private static SessionManager sessionManager = null;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Constructor
    private SessionManager(Context context) {
        if (context != null) {
            pref = SampleApplication.getAppContext().getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
            editor = pref.edit();
            editor.apply();
        }
    }

    public static SessionManager getInstance(){
        if(sessionManager == null){
            sessionManager = new SessionManager(SampleApplication.getAppContext());
        }
        return sessionManager;
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return pref.getBoolean(KEY_USER_LOGGED_IN, false);
    }

    public void setUserLoggedIn(boolean value) {
        editor.putBoolean(KEY_USER_LOGGED_IN, value);
        editor.commit();
    }

    public String getData() {
        return pref.getString(KEY_JSON_DATA, null);
    }

    public void setData(String data) {
        editor.putString(KEY_JSON_DATA, data);
        editor.commit();
    }
}
