package com.example.sampletest.view.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.presenter.HomepagePresenter;
import com.example.sampletest.utils.Constants;
import com.example.sampletest.utils.SessionManager;
import com.example.sampletest.view.BaseFragment;
import com.example.sampletest.view.BaseNavigationActivity;
import com.example.sampletest.view.adapter.HomePageAdapter;
import com.google.gson.Gson;

import butterknife.BindView;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class HomePageFragment extends BaseFragment implements HomePageView {

    private static final String TAG = HomePageFragment.class.getSimpleName();

    @BindView(R.id.rlHome)
    RecyclerView mRecyclerViewHome;
    @BindView(R.id.header)
    RelativeLayout mHeader;
    private HomepagePresenter mPresenter;

    public static HomePageFragment newInstance(Bundle bundle) {
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new HomepagePresenter(this);
        initRecyclerView();
        showWaiting(true);
        if (SessionManager.getInstance().getData() != null) {
            DataModel dataModel = new Gson().fromJson(SessionManager.getInstance().getData(), DataModel.class);
            getHomePageData(dataModel);
        } else {
            mPresenter.getHomePageData();
        }
    }

    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewHome.setLayoutManager(manager);
        mRecyclerViewHome.setHasFixedSize(true);
        mRecyclerViewHome.setItemViewCacheSize(20);
        mRecyclerViewHome.setDrawingCacheEnabled(true);
        mRecyclerViewHome.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getHomePageData(DataModel dataModel) {
        if (SessionManager.getInstance().getData() == null) {
            SessionManager.getInstance().setData(new Gson().toJson(dataModel));
        }
        hideWaiting();
        mHeader.setVisibility(View.VISIBLE);
        HomePageAdapter categoryAdapter = new HomePageAdapter(this, dataModel.getCategories());
        mRecyclerViewHome.setAdapter(categoryAdapter);
    }

    @Override
    public void showError(int resId, Object... args) {

    }

    @Override
    public void showError(String errorMessage) {
        if(errorMessage.equals("Network Error")){
            showNetworkNotAvailable();
        } else {
            showErrorDialog(errorMessage, true);
        }
    }

    @Override
    public void showWaiting(boolean isCancelable) {
        showProgressDialog(true);
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

