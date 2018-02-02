package com.example.sampletest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder> {

    StringBuilder mStringBuilder;
    private List<DataModel.CategoriesBean.ProductsBean.VariantsBean> variants;

    public ProductSizeAdapter(List<DataModel.CategoriesBean.ProductsBean.VariantsBean> variants) {
        this.variants = variants;
        mStringBuilder = new StringBuilder();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_product_size, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataModel.CategoriesBean.ProductsBean.VariantsBean sizeEntity = variants.get(position);
        if (sizeEntity.getColor().length() > 0) {
            mStringBuilder.setLength(0);
            mStringBuilder.trimToSize();
            mStringBuilder.append(sizeEntity.getColor()).
                    append(" (").append(sizeEntity.getSize()).append(")");
            holder.txtProductSize.setText(mStringBuilder.toString());
        } else {
            holder.txtProductSize.setText(String.valueOf(sizeEntity.getSize()));
        }
    }

    @Override
    public int getItemCount() {
        return (null != variants ? variants.size() : 0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pack_size_item_text)
        TextView txtProductSize;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataModel.CategoriesBean.ProductsBean.VariantsBean sizeEntity = variants.get(getAdapterPosition());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.setLength(0);
                    stringBuilder.trimToSize();
                    stringBuilder.append(sizeEntity.getColor()).
                            append(" (").append(sizeEntity.getSize()).append(")");
                    Toast.makeText(v.getContext(), stringBuilder.toString() + " Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}