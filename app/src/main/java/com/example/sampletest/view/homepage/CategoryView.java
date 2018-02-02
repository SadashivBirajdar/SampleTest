package com.example.sampletest.view.homepage;

import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.BaseView;
import com.example.sampletest.view.ShowErrorMessages;
import com.example.sampletest.view.ShowWaitingLoaders;

public interface CategoryView extends BaseView, ShowWaitingLoaders, ShowErrorMessages {

    void categoryClicked(DataModel.CategoriesBean categoriesBean);
}
