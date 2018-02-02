package com.example.sampletest.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import com.example.sampletest.R;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
    }

    /**
     * made by Mayank
     * to show a back arrow in activity, as required in Login
     */
    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * made by Mayank
     * to change title in activity, as required in Login
     */
    public void changeToolbarTitle(String title) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }

    public void startActivityTransition() {
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public void exitActivityTransition() {
        overridePendingTransition(R.anim.back_in, R.anim.back_out);
    }
}
