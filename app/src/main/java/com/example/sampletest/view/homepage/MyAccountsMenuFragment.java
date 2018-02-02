package com.example.sampletest.view.homepage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sampletest.view.BaseFragment;
import com.example.sampletest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountsMenuFragment extends BaseFragment {

    public static final String CATEGORIES = "categories";

    @BindView(R.id.layoutList)
    LinearLayout rootLayout;
    @BindView(R.id.menu_name)
    TextView mMenuName;

    private ArrayList<String> mMyAccountsMenuList;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        MyAccountsMenuFragment fragment = new MyAccountsMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_shop_by_category_menu;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mMyAccountsMenuList = bundle.getStringArrayList(CATEGORIES);
        mMyAccountsMenuList = new ArrayList<>();
        mMyAccountsMenuList.add(getResources().getString(R.string.personal_info));
        mMyAccountsMenuList.add(getResources().getString(R.string.address_book));
        mMyAccountsMenuList.add(getResources().getString(R.string.past_orders));
        mMyAccountsMenuList.add(getResources().getString(R.string.change_password));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMenuName.setText(getActivity().getResources().getString(R.string.nav_menu_my_account_label));
        if (mMyAccountsMenuList != null) {
            populateAccountMenu(mMyAccountsMenuList);
        }
    }

    private void populateAccountMenu(final List<String> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final View singleRow = getActivity().getLayoutInflater().inflate(R.layout.navigation_menu_single_row, null);

                final TextView textView = singleRow.findViewById(R.id.single_row_text);
                final ImageView imageView = singleRow.findViewById(R.id.single_row_arrow);
                imageView.setVisibility(View.GONE);
                textView.setText(list.get(i));
                singleRow.setId(i);
                rootLayout.addView(singleRow);
            }
        }
    }

    @OnClick(R.id.imgBack)
    public void backPressed() {
        getActivity().onBackPressed();
    }
}




