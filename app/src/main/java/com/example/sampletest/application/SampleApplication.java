package com.example.sampletest.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by emb-sadabir on 2/2/18.
 */
public class SampleApplication extends Application {

    public static SampleApplication sSampleApplication;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static SampleApplication getAppContext() {
        return sSampleApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sSampleApplication = this;
    }
}
