package com.example.sampletest.view.homepage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.utils.Constants;
import com.example.sampletest.utils.SessionManager;
import com.example.sampletest.view.BaseFragment;
import com.example.sampletest.view.BaseNavigationActivity;
import com.example.sampletest.view.adapter.CategoryAdapter;
import com.example.sampletest.view.productList.ProductListActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopByCategoryMenuFragment extends BaseFragment implements CategoryView{

    @BindView(R.id.categoryList)
    RecyclerView mRecyclerViewHome;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ShopByCategoryMenuFragment fragment = new ShopByCategoryMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shop_by_category_menu;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
        if (SessionManager.getInstance().getData() != null) {
            DataModel dataModel = new Gson().fromJson(SessionManager.getInstance().getData(), DataModel.class);
            updateUI(dataModel);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewHome.setLayoutManager(linearLayoutManager);
        mRecyclerViewHome.setHasFixedSize(true);
        mRecyclerViewHome.setItemViewCacheSize(20);
        mRecyclerViewHome.setDrawingCacheEnabled(true);
        mRecyclerViewHome.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    private void updateUI(DataModel dataModel) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, dataModel.getCategories());
        mRecyclerViewHome.setAdapter(categoryAdapter);
    }

    @OnClick(R.id.imgBack)
    public void backPressed(){
        getActivity().onBackPressed();
    }

    @Override
    public void showError(int resId, Object... args) {

    }

    @Override
    public void showError(String errorMessage) {
        showErrorDialog(errorMessage, true);
    }

    @Override
    public void showWaiting(boolean isCancelable) {
        showProgressDialog(isCancelable);
    }

    @Override
    public void hideWaiting() {
        hideDialog();
    }

    @Override
    public void categoryClicked(DataModel.CategoriesBean categoriesBean) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PLP_TAG, categoriesBean.getName());
        ((BaseNavigationActivity)getActivity()).openPlp(bundle);
    }
}