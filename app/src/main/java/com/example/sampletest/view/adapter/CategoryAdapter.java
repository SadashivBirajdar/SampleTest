package com.example.sampletest.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.homepage.CategoryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private final List<DataModel.CategoriesBean> categoriesBeanList;
    private final CategoryView mView;

    public CategoryAdapter(CategoryView categoryView, List<DataModel.CategoriesBean> categories) {
        categoriesBeanList = categories;
        this.mView = categoryView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DataModel.CategoriesBean categoriesBean = categoriesBeanList.get(position);
        if (!categoriesBean.getName().isEmpty()) {
            holder.categoryName.setText(categoriesBean.getName());
        }
        holder.subCategory.setVisibility(View.GONE);
        if (categoriesBean.getChild_categories() != null && categoriesBean.getChild_categories().size() > 0) {
            holder.imgArrow.setVisibility(View.VISIBLE);
            holder.subCategory.setLayoutManager(new LinearLayoutManager(mView.getContext()));
            SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(mView, categoriesBean.getChild_categories());
            holder.subCategory.setAdapter(subCategoryAdapter);
        } else {
            holder.imgArrow.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return categoriesBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.single_row_text)
        TextView categoryName;
        @BindView(R.id.rlSubCategory)
        RecyclerView subCategory;
        @BindView(R.id.single_row_arrow)
        ImageView imgArrow;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.single_row_arrow, R.id.single_row_text})
        public void rowClicked(View view) {
            DataModel.CategoriesBean categoriesBean = categoriesBeanList.get(getAdapterPosition());
            switch (view.getId()) {
                case R.id.single_row_arrow:
                    if (categoriesBean.getChild_categories() != null &&
                            categoriesBean.getChild_categories().size() > 0 && !subCategory.isShown()) {
                        subCategory.setVisibility(View.VISIBLE);
                        imgArrow.animate().rotationXBy(180).start();
                    } else {
                        subCategory.setVisibility(View.GONE);
                        imgArrow.animate().rotationXBy(180).start();
                    }
                    break;
                case R.id.single_row_text:
                    mView.categoryClicked(categoriesBean);
                    break;
            }
        }
    }
}
