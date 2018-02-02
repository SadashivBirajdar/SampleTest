package com.example.sampletest.view.homepage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.utils.Constants;
import com.example.sampletest.utils.SessionManager;
import com.example.sampletest.view.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class NavigationViewFragment extends BaseFragment implements NavigationView {

    private static final String TAG = NavigationViewFragment.class.getSimpleName();
    @BindView(R.id.layProfile)
    RelativeLayout layProfile;
    @BindView(R.id.header_login_register_button)
    Button loginButton;
    @BindView(R.id.header_login_register_button_or_username_containing_linearLayout)
    RelativeLayout headerLoginRegisterButtonOrUsernameContainingLinearLayout;
    @BindView(R.id.header_logged_in_user_name)
    TextView headerLoggedInUserName;
    @BindView(R.id.personal_info_profile_pic)
    AppCompatImageView userProfileImage;
    @BindView(R.id.home_directing_layout)
    LinearLayout homeDirectingLayout;
    @BindView(R.id.home_label_textView)
    TextView homeLabelTextView;
    @BindView(R.id.my_account_layout)
    LinearLayout myAccountLayout;
    @BindView(R.id.my_accounts_label_textView)
    TextView myAccountsLabelTextView;
    @BindView(R.id.my_account_layout_content_layout)
    LinearLayout myAccountLayoutContentLayout;
    @BindView(R.id.my_shopping_list_directing_layout)
    LinearLayout myShoppingListDirectingLayout;
    @BindView(R.id.navigation_menu_fragment_root_view)
    LinearLayout myRootView;
    @BindView(R.id.my_shopping_list_label_textView)
    TextView myShoppingListLabelTextView;
    @BindView(R.id.shop_by_category_layout)
    LinearLayout shopByCategoryLayout;
    @BindView(R.id.shop_by_category_label_textView)
    TextView shopByCategoryLabelTextView;
    @BindView(R.id.shop_by_offers_directing_layout)
    LinearLayout shopByOffersDirectingLayout;
    @BindView(R.id.offers_label_textView)
    TextView offersLabelTextView;
    LinearLayout myCustomerCareLayout;
    @BindView(R.id.customer_care_label_textView)
    TextView customerCareLabelTextView;
    @BindView(R.id.my_customer_care_layout_content_layout)
    LinearLayout myCustomerCareLayoutContentLayout;
    @BindView(R.id.log_out_layout)
    LinearLayout logOutLayout;
    @BindView(R.id.my_accounts_arrow_icon)
    ImageView myAccountArrowIcon;
    @BindView(R.id.customer_care_arrow_icon)
    ImageView customerCareArrowIcon;
    @BindView(R.id.navigation_fragment_scrollView)
    NestedScrollView fragmentScrollView;
    NavigationViewFragmentListener myListener;

    private AlertDialog.Builder mAlertDialogBuilder;
    private AlertDialog mAlertDialog;
    public static NavigationViewFragment newInstance() {
        Bundle args = new Bundle();
        NavigationViewFragment fragment = new NavigationViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        userProfileImage.setImageResource(R.drawable.ic_profile_placeholder);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        myListener = (NavigationViewFragmentListener) getActivity();
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        checkForLoginUser();
    }

    private void checkForLoginUser() {
        if (SessionManager.getInstance().isUserLoggedIn()) {
            loginButton.setVisibility(View.GONE);
            headerLoggedInUserName.setVisibility(View.VISIBLE);
            headerLoggedInUserName.setText("Test");
            showMyAccountsOption();
            showMyShoppingListOption();
            logOutLayout.setVisibility(View.VISIBLE);
        } else {
            fragmentScrollView.fullScroll(View.FOCUS_UP);
            myShoppingListDirectingLayout.setVisibility(View.GONE);
            myAccountLayoutContentLayout.setVisibility(View.GONE);
            myAccountLayout.setVisibility(View.GONE);
            logOutLayout.setVisibility(View.GONE);
            loginButton.setVisibility(View.VISIBLE);
            headerLoggedInUserName.setVisibility(View.GONE);
            userProfileImage.setImageResource(R.drawable.ic_profile_placeholder);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_navigation_view;
    }

    public void showMyAccountsOption() {
        myAccountLayout.setVisibility(View.VISIBLE);
    }

    public void showMyShoppingListOption() {
        myShoppingListDirectingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(int resId, Object... args) {
    }

    @Override
    public void showError(String errorMessage) {
    }

    @Override
    public void showWaiting(boolean isCancelable) {
        showProgressDialog(isCancelable);
    }

    @Override
    public void hideWaiting() {
        hideDialog();
    }

    /* ALL THE ONCLICK LISTENERS*/
    @OnClick(R.id.shop_by_category_layout)
    public void onCategoryLayoutClicked() {
        if (myListener != null) {
            myListener.changeToCategoriesMenu();
        }
    }

    @OnClick(R.id.home_label_textView)
    public void onHomeClick() {
        if (myListener != null) {
            myListener.openHomePage();
        }
    }

    @OnClick(R.id.my_account_layout)
    public void onMyAccountClicked() {
        if (myListener != null) {
            myListener.changeToMyAccountsMenu();
        }
    }

    @OnClick(R.id.my_shopping_list_directing_layout)
    public void onMyShoppingListClicked() {
        if (myListener != null) {
            myListener.openShoppingList();
        }
    }

    @OnClick(R.id.my_customer_care_layout)
    public void onCustomerCareClicked() {
        if (myListener != null) {
            myListener.openCustomerCare();
        }
    }

    @OnClick(R.id.header_login_register_button)
    public void onHeaderLoginButtonCLickedClicked() {
        if (myListener != null) {
            myListener.performLogin();
        }
    }

    @OnClick(R.id.log_out_layout)
    public void onLogoutClicked() {
        SessionManager.getInstance().setUserLoggedIn(false);
        logoutDialog("Logged Out Successfully");
    }

    private void logoutDialog(String titleMessage) {

        if (!isAlertDialogShowing(mAlertDialog)) {
            mAlertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.AppAlertDialogTheme);
            mAlertDialogBuilder.setTitle(Constants.ALERT_TITLE).setMessage(titleMessage).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (myListener != null)
                        myListener.performLogout();
                    dialog.dismiss();
                    hideDialog();
                }
            });
            mAlertDialog = mAlertDialogBuilder.create();
            mAlertDialog.setCancelable(false);
            mAlertDialog.show();
        }
    }
}

