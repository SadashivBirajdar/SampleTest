package com.example.sampletest.view.productList;

import android.os.Bundle;
import android.view.View;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class ProductListFragment extends BasePlpFragment {

    public static final String TAG = ProductListFragment.class.getSimpleName();

    public static ProductListFragment newInstance(Bundle args) {
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void checkTotalCount(int totalResults) {
        if (totalResults == 0) {
            mTxtNoProducts.setVisibility(View.VISIBLE);
            mBtnContinueShopping.setVisibility(View.VISIBLE);
            mPlpBlackHeader.setVisibility(View.GONE);
            mRvProductList.setVisibility(View.GONE);
        } else {
            mTxtTotalItems.setText(String.valueOf(totalResults) + " Products");
            setUiVisibility();
        }
    }

    @Override
    protected void setUiVisibility() {
        mPlpBlackHeader.setVisibility(View.VISIBLE);
        mTxtNoProducts.setVisibility(View.GONE);
        mBtnContinueShopping.setVisibility(View.GONE);
        mRvProductList.setVisibility(View.VISIBLE);
    }
}
