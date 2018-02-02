package com.example.sampletest.presenter;

import com.example.sampletest.network.RestClient;
import com.example.sampletest.network.RestInterface;
import com.example.sampletest.view.BaseView;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class BasePresenter<T extends BaseView> {

    protected T mView;
    protected RestInterface mApiInterface;
    public BasePresenter(T view) {
        mView = view;
        getApiInterface();
    }

    void getApiInterface(){
        mApiInterface = RestClient.getAPIInterface();
    }
}
