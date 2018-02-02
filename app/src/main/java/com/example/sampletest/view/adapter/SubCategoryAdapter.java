package com.example.sampletest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.homepage.CategoryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {

    private final List<Object> categoriesBeanList;
    private final CategoryView mView;

    public SubCategoryAdapter(CategoryView categoryView, List<Object> categories) {
        categoriesBeanList = categories;
        this.mView = categoryView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_menu_single_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Object obj = categoriesBeanList.get(position);
        if(obj != null){
            holder.categoryName.setText(String.valueOf(obj));
        }
        holder.imgArrow.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return categoriesBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.single_row_text)
        TextView categoryName;
        @BindView(R.id.single_row_arrow)
        ImageView imgArrow;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
