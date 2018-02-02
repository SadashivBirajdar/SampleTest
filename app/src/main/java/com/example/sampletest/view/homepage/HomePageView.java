package com.example.sampletest.view.homepage;

import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.BaseView;
import com.example.sampletest.view.ShowErrorMessages;
import com.example.sampletest.view.ShowWaitingLoaders;

import java.util.ArrayList;

public interface HomePageView extends BaseView, ShowErrorMessages, ShowWaitingLoaders {

    void getHomePageData(DataModel dataModel);

    void categoryClicked(DataModel.CategoriesBean categoriesBean);
}
