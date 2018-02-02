package com.example.sampletest.view.productList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.sampletest.R;
import com.example.sampletest.utils.Constants;
import com.example.sampletest.view.BaseNavigationActivity;
import com.example.sampletest.view.homepage.HomePageActivity;


/**
 * Created by emb-sadabir on 2/2/18.
 */

public class ProductListActivity extends BaseNavigationActivity {

    ProductListFragment mProductListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra(Constants.BUNDLE_PLP);
        mProductListFragment = ProductListFragment.newInstance(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,
                mProductListFragment).commit();
    }

    @Override
    protected void callHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        startActivityTransition();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mProductListFragment != null) {
            getSupportFragmentManager().popBackStackImmediate();
            Bundle bundle = intent.getBundleExtra(Constants.BUNDLE_PLP);
            mProductListFragment = ProductListFragment.newInstance(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.content,
                    mProductListFragment).commit();
        }
    }
}
