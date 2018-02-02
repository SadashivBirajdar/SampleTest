package com.example.sampletest.presenter;

import com.example.sampletest.model.DataModel;
import com.example.sampletest.utils.Utils;
import com.example.sampletest.view.homepage.HomePageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class HomepagePresenter extends BasePresenter<HomePageView> {

    public HomepagePresenter(HomePageView view) {
        super(view);
    }

    public void getHomePageData() {
        if(Utils.isNetworkAvailable(mView.getContext())) {
            mApiInterface.getData().enqueue(new Callback<DataModel>() {
                @Override
                public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                    if (mView != null) {
                        if (response.isSuccessful()) {
                            mView.getHomePageData(response.body());
                        } else {
                            mView.showError("Api Failed");
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataModel> call, Throwable t) {
                    if (mView != null) {
                        mView.showError(t.getLocalizedMessage());
                    }
                }
            });
        } else {
            mView.showError("Network Error");
        }
    }
}
