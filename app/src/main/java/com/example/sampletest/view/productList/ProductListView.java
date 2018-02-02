package com.example.sampletest.view.productList;


import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.BaseView;
import com.example.sampletest.view.ShowErrorMessages;
import com.example.sampletest.view.ShowWaitingLoaders;

import java.util.List;

public interface ProductListView extends BaseView, ShowErrorMessages, ShowWaitingLoaders {

    void productList(List<DataModel.CategoriesBean.ProductsBean> parameter);

    void applySort(DataModel.RankingsBean rankingsBean);
}
