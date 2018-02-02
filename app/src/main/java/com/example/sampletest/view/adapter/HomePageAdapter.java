package com.example.sampletest.view.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.homepage.HomePageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.MyViewHolder> {

    private final List<DataModel.CategoriesBean> categoriesBeanList;
    private final HomePageView mView;

    public HomePageAdapter(HomePageView homePageView, List<DataModel.CategoriesBean> categories) {
        categoriesBeanList = categories;
        this.mView = homePageView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        DataModel.CategoriesBean categoriesBean = categoriesBeanList.get(position);
        if (!categoriesBean.getName().isEmpty()) {
            holder.categoryName.setText(categoriesBean.getName());
        }
        if(position % 2 == 0){
            holder.rowLayout.setBackgroundColor(ContextCompat.getColor(mView.getContext(), R.color.colorPrimary));
        } else {
            holder.rowLayout.setBackgroundColor(ContextCompat.getColor(mView.getContext(), R.color.colorAccent));
        }
    }

    @Override
    public int getItemCount() {
        return categoriesBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.single_row_text)
        TextView categoryName;
        @BindView(R.id.rowLayout)
        RelativeLayout rowLayout;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mView.categoryClicked(categoriesBeanList.get(getAdapterPosition()));
                }
            });
        }
    }
}
