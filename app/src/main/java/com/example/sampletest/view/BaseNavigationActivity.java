package com.example.sampletest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampletest.R;
import com.example.sampletest.utils.Constants;
import com.example.sampletest.view.homepage.CustomerCareMenuFragment;
import com.example.sampletest.view.homepage.MyAccountsMenuFragment;
import com.example.sampletest.view.homepage.NavigationViewFragment;
import com.example.sampletest.view.homepage.NavigationViewFragmentListener;
import com.example.sampletest.view.homepage.ShopByCategoryMenuFragment;
import com.example.sampletest.view.login.LoginActivity;
import com.example.sampletest.view.productList.ProductListActivity;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public abstract class BaseNavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationViewFragmentListener,
        DrawerLayout.DrawerListener{

    public static final String TAG = BaseNavigationActivity.class.getSimpleName();
    protected final String NAVIGATION_MENU = NavigationViewFragment.class.getSimpleName();
    private final String MY_CATEGORY_MENU = ShopByCategoryMenuFragment.class.getSimpleName();
    private final String MY_ACCOUNT_MENU = MyAccountsMenuFragment.class.getSimpleName();
    private final String CUSTOMER_CARE_MENU = CustomerCareMenuFragment.class.getSimpleName();

    protected DrawerLayout mDrawer;
    private AppBarLayout appBarLayout;
    private ImageView mIvHomeIcon;

    protected NavigationViewFragment navigationViewFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initUI();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout.bringToFront();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, null, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        mDrawer.addDrawerListener(this);
        toggle.syncState();

        mIvHomeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        navigationViewFragment = NavigationViewFragment.newInstance();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.navigation_menu_fragment, navigationViewFragment, NAVIGATION_MENU)
                .commit();
    }

    private void initUI() {
        appBarLayout = findViewById(R.id.appBarLayout);
        mIvHomeIcon = findViewById(R.id.hamburger_icon);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            popFragment();
        } else {
            super.onBackPressed();
            exitActivityTransition();
        }
    }

    protected void popFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment topFragment = supportFragmentManager.findFragmentById(R.id.navigation_menu_fragment);
        if (topFragment instanceof ShopByCategoryMenuFragment ||
                topFragment instanceof MyAccountsMenuFragment ||
                topFragment instanceof CustomerCareMenuFragment) {
            supportFragmentManager.beginTransaction().remove(topFragment).commit();
            Fragment navigationFragment = supportFragmentManager.findFragmentByTag(NAVIGATION_MENU);
            if (navigationFragment != null) {
                supportFragmentManager.beginTransaction().remove(navigationFragment).commit();
            }
            navigationViewFragment = NavigationViewFragment.newInstance();
            supportFragmentManager.beginTransaction().replace(R.id.navigation_menu_fragment, navigationViewFragment, NAVIGATION_MENU).commit();
        } else {
            mDrawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void openHomePage() {
        mDrawer.closeDrawer(GravityCompat.START);
        callHomePage();
    }

    protected abstract void callHomePage();

    @Override
    public void openCustomerCare() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit)
                .replace(
                        R.id.navigation_menu_fragment,
                        CustomerCareMenuFragment.newInstance(),
                        CUSTOMER_CARE_MENU)
                .addToBackStack(CUSTOMER_CARE_MENU)
                .commit();
    }

    @Override
    public void performLogout() {
        callHomePage();
    }

    @Override
    public void openShoppingList() {
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void performLogin() {
        mDrawer.closeDrawer(GravityCompat.START);
        startActivity(new Intent(this, LoginActivity.class));
        startActivityTransition();
    }

    @Override
    public void changeToCategoriesMenu() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit)
                .replace(
                        R.id.navigation_menu_fragment,
                        ShopByCategoryMenuFragment.newInstance(),
                        MY_CATEGORY_MENU)
                .addToBackStack(MY_CATEGORY_MENU)
                .commit();

    }

    @Override
    public void changeToMyAccountsMenu() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit)
                .replace(
                        R.id.navigation_menu_fragment,
                        MyAccountsMenuFragment.newInstance(),
                        MY_ACCOUNT_MENU)
                .addToBackStack(MY_ACCOUNT_MENU)
                .commit();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        popFragment();
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        Log.d(TAG, "onDrawerStateChanged: ");
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    public void openPlp(Bundle bundle) {
        mDrawer.closeDrawer(GravityCompat.START);
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra(Constants.BUNDLE_PLP, bundle);
        startActivity(intent);
        startActivityTransition();
    }
}
