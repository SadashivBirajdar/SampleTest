package com.example.sampletest.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.example.sampletest.R;
import com.example.sampletest.view.BaseActivity;
import com.example.sampletest.view.homepage.HomePageActivity;

/**
 * Login Activity just to hold a frameLayout to load Login Fragment
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,
                LoginFragment.newInstance()).commit();
        showBackArrow();
        changeToolbarTitle(getResources().getString(R.string.login_activity_title));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitActivityTransition();
    }

    protected void callHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        startActivityTransition();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base;
    }

}
