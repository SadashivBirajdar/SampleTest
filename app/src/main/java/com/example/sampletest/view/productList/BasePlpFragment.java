package com.example.sampletest.view.productList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.presenter.ProductListPresenter;
import com.example.sampletest.utils.Constants;
import com.example.sampletest.utils.DividerItemDecoration;
import com.example.sampletest.utils.SessionManager;
import com.example.sampletest.utils.Utils;
import com.example.sampletest.view.BaseFragment;
import com.example.sampletest.view.BaseNavigationActivity;
import com.example.sampletest.view.adapter.ProductListAdapter;
import com.example.sampletest.view.adapter.SortAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public abstract class BasePlpFragment extends BaseFragment implements ProductListView {

    public static final String TAG = BasePlpFragment.class.getSimpleName();
    @BindView(R.id.noProducts)
    protected TextView mTxtNoProducts;
    @BindView(R.id.plp_header)
    protected RelativeLayout mPlpBlackHeader;
    @BindView(R.id.rvProductList)
    protected RecyclerView mRvProductList;

    @BindView(R.id.button_continue_shopping)
    protected Button mBtnContinueShopping;
    @BindView(R.id.new_filter_layout)
    protected RelativeLayout mSortDialogLayout;

    // total items
    @BindView(R.id.txtTotalItems)
    protected TextView mTxtTotalItems;
    //sort layout
    @BindView(R.id.imgSortLayout)
    LinearLayout mLayoutSort;
    @BindView(R.id.imgSort)
    ImageView mSortIconImageView;
    @BindView(R.id.imgSort_text)
    TextView mSortIconTextView;
    private ProductListPresenter mPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ProductListPresenter(this);
        mSortDialogLayout.setVisibility(View.GONE);
        initRecyclerView();
        showWaiting(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String from = bundle.getString(Constants.PLP_TAG);
            if (from != null) {
                mPresenter.getProductList(from);
            }
        }
    }

    protected abstract void setUiVisibility();

    protected void initRecyclerView() {
        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider);
        mRvProductList.addItemDecoration(new DividerItemDecoration(dividerDrawable));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_plp;
    }

    @OnClick({R.id.imgSortLayout, R.id.button_continue_shopping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSortLayout:
                showSortDialog();
                break;
            case R.id.button_continue_shopping:
                if (getActivity() instanceof BaseNavigationActivity) {
                    ((BaseNavigationActivity) getActivity()).openHomePage();
                }
                break;
        }
    }

    private void showSortDialog() {
        if (mSortDialogLayout.getVisibility() == View.VISIBLE) {
            mSortDialogLayout.setVisibility(View.GONE);
        } else {
            if (mSortDialogLayout.getChildCount() == 0) {
                makeNewFilterLayout();
                mSortDialogLayout.setVisibility(View.GONE);
                mSortDialogLayout.setVisibility(View.VISIBLE);
            } else {
                mSortDialogLayout.setVisibility(View.GONE);
                mSortDialogLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void makeNewFilterLayout() {
        View newCustomSortLayout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_filter, null);
        TextView sortLabel = (TextView) newCustomSortLayout.findViewById(R.id.txtSortLabel);
        sortLabel.setTypeface(Utils.setTypeFace(getActivity(), true));
        ImageView closeDialog = (ImageView) newCustomSortLayout.findViewById(R.id.iv_sort_cancel);
        RecyclerView rvSortLabel = (RecyclerView) newCustomSortLayout.findViewById(R.id.rv_sort_label);
        rvSortLabel.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (SessionManager.getInstance().getData() != null) {
            DataModel dataModel = new Gson().fromJson(SessionManager.getInstance().getData(), DataModel.class);
            SortAdapter mSortAdapter = new SortAdapter(this, dataModel.getRankings());
            rvSortLabel.setAdapter(mSortAdapter);
        }
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSortDialogLayout.removeAllViews();               //Remove all its children
                mSortDialogLayout.setVisibility(View.GONE);       //Hide It
                mSortDialogLayout.removeAllViews();               //Remove all its children
                mSortDialogLayout.setVisibility(View.GONE);       //Hide It
            }
        });
        mSortDialogLayout.addView(newCustomSortLayout);
    }

    @Override
    public void applySort(DataModel.RankingsBean rankingsBean) {
        mSortDialogLayout.removeAllViews();
        mSortDialogLayout.setVisibility(View.GONE);
        mSortDialogLayout.removeAllViews();
        mSortDialogLayout.setVisibility(View.GONE);
        checkSortLayout();
    }

    protected void checkSortLayout() {
        mLayoutSort.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.plp_sort_filter_header_background_selected));
        mSortIconImageView.setColorFilter(ContextCompat.getColor(getActivity(), R.color.secondary_color_white));
        mSortIconTextView.setTextColor(ContextCompat.getColor(getActivity(), R.color.secondary_color_white));
    }

    @Override
    public void productList(List<DataModel.CategoriesBean.ProductsBean> productsBeans) {
        hideWaiting();
        int totalResults = productsBeans.size();
        checkTotalCount(totalResults);
        setUpAdapter(productsBeans);
    }

    private void setUpAdapter(List<DataModel.CategoriesBean.ProductsBean> productsBeans) {
        ProductListAdapter adapter = new ProductListAdapter(this, productsBeans);
        mRvProductList.setAdapter(adapter);
    }

    protected abstract void checkTotalCount(int totalResults);

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
}
