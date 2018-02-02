package com.example.sampletest.view.login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampletest.R;
import com.example.sampletest.presenter.LoginPagePresenter;
import com.example.sampletest.utils.CustomEditText;
import com.example.sampletest.utils.Utils;
import com.example.sampletest.utils.ValidationUtil;
import com.example.sampletest.view.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginPageView {

    @BindView(R.id.user_mobile_number)
    CustomEditText userMobileNumber;
    @BindView(R.id.user_password)
    CustomEditText userPassword;
    @BindView(R.id.forgot_password_textView)
    TextView forgotPasswordTextView;
    @BindView(R.id.user_login_button)
    Button userLoginButton;
    @BindView(R.id.user_register_button)
    Button userRegisterButton;
    private LoginPagePresenter mPresenter;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new LoginPagePresenter(this);
        userLoginButton.setTypeface(Utils.setTypeFace(getActivity(), true));
        userRegisterButton.setTypeface(Utils.setTypeFace(getActivity(), true));
        userMobileNumber.setHintInEdittext(
                getActivity().getResources().getString(R.string.mobile_number));
    }

    @OnClick(R.id.user_login_button)
    void onUserLoginButtonClick() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (userLoginButton != null)
            inputMethodManager.hideSoftInputFromWindow(userLoginButton.getWindowToken(), 0);
        if (Utils.isNetworkAvailable(getActivity())) {
            boolean validationError = false;
            ValidationUtil validateUtil = new ValidationUtil();
            if (!validateUtil.isNotNull(userMobileNumber.getText())) {
                userMobileNumber.setErrorMsg(getString(R.string.invalid_number));
                validationError = true;
            }
            if (!validateUtil.validateNumber(userMobileNumber.getText())) {
                userMobileNumber.setErrorMsg(getString(R.string.invalid_number));
                validationError = true;
            }
            if (!validateUtil.isNotNull(userPassword.getText())) {
                userPassword.setErrorMsg(getString(R.string.validate_no_password_entered));
                validationError = true;
            }
            if (!validationError) {
                mPresenter.getLogin(userMobileNumber.getText().trim(),
                        userPassword.getText().trim());
            }
        } else {
            showNetworkNotAvailable();
        }
    }

    @OnClick(R.id.forgot_password_textView)
    void onForgotPasswordButtonClick() {
        Toast.makeText(getActivity(), "Link to forgot password", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.user_register_button)
    void onUserRegisterButtonClick() {
        Toast.makeText(getActivity(), "Link to registration", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void showError(int resId, Object... args) {

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showWaiting(boolean isCancelable) {

    }

    @Override
    public void hideWaiting() {

    }
}
