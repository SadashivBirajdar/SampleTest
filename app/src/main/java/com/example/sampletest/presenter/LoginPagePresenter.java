package com.example.sampletest.presenter;

import com.example.sampletest.view.login.LoginPageView;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class LoginPagePresenter extends BasePresenter<LoginPageView> {


    public LoginPagePresenter(LoginPageView view) {
        super(view);
    }

    /**
     * To login a user.
     *
     * @param userNumber   entered in Login Screen
     * @param userPassword entered in Login Screen
     */
    public void getLogin(String userNumber,
                         String userPassword) {

    }
}
