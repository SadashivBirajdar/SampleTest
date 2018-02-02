package com.example.sampletest.presenter;

import com.example.sampletest.model.DataModel;
import com.example.sampletest.utils.SessionManager;
import com.example.sampletest.view.productList.ProductListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by emb-sadabir on 2/2/18.
 */

public class ProductListPresenter extends BasePresenter<ProductListView> {


    public ProductListPresenter(ProductListView view) {
        super(view);
    }

    public void getProductList(String categoryKey) {
        if (SessionManager.getInstance().getData() != null) {
            DataModel dataModel = new Gson().fromJson(SessionManager.getInstance().getData(), DataModel.class);
            List<DataModel.CategoriesBean> categories = dataModel.getCategories();
            for(DataModel.CategoriesBean categoriesBean : categories){
                if(categoriesBean.getName() != null && categoriesBean.getName().equals(categoryKey)){
                    mView.productList(categoriesBean.getProducts());
                    break;
                }
            }
        }
    }
}
