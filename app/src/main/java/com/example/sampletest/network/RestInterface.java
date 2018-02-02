package com.example.sampletest.network;


import com.example.sampletest.model.DataModel;
import com.example.sampletest.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by emb-sadabir on 2/2/18.
 */

public interface RestInterface {


    @GET(Constants.API_HOME_DATA)
    Call<DataModel> getData();
}
