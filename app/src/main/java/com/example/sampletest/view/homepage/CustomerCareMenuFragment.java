package com.example.sampletest.view.homepage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerCareMenuFragment extends BaseFragment{

    @BindView(R.id.imgBack)
    ImageView mImgBackBtn;
    @BindView(R.id.layoutList)
    LinearLayout rootLayout;
    @BindView(R.id.menu_name)
    TextView mMenuName;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        CustomerCareMenuFragment fragment = new CustomerCareMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_customer_care_menu;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<String> customerCareMenuOptionsList = new ArrayList<>();
        customerCareMenuOptionsList.add(getResources().getString(R.string.contact_us));
        customerCareMenuOptionsList.add(getResources().getString(R.string.faq_s));
        customerCareMenuOptionsList.add(getResources().getString(R.string.about_us));
        customerCareMenuOptionsList.add(getResources().getString(R.string.terms_and_conditions));
        customerCareMenuOptionsList.add("Privacy Policy");
        if(customerCareMenuOptionsList != null) {
            populateCustomerCareList(customerCareMenuOptionsList);
        }
        mMenuName.setText(getActivity().getResources().getString(R.string.customer_care_title));
    }

    private void populateCustomerCareList(final List<String> list){

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
    public void backPressed(){
        getActivity().onBackPressed();
    }
}




