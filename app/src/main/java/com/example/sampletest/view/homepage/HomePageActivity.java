package com.example.sampletest.view.homepage;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.example.sampletest.R;
import com.example.sampletest.view.BaseNavigationActivity;

public class HomePageActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomePageFragment homePageFragment = HomePageFragment.newInstance(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.content, homePageFragment, HomePageFragment.class.getSimpleName())
                .addToBackStack(HomePageFragment.class.getSimpleName()).commit();
    }

    @Override
    protected void callHomePage() {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            popFragment();
        } else {
            finish();
        }
    }
}
