package com.example.sampletest.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.productList.BasePlpFragment;
import com.example.sampletest.view.productList.ProductListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final ProductListView mProductListView;
    protected List<DataModel.CategoriesBean.ProductsBean> mProductsEntities;

    public ProductListAdapter(ProductListView productListView, List<DataModel.CategoriesBean.ProductsBean> itemsList) {
        this.mProductsEntities = itemsList;
        this.mProductListView = productListView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_plp, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Item ViewHolder
        bindItemViewHolder((ItemViewHolder) holder, position);
    }

    private void bindItemViewHolder(final ItemViewHolder holder, final int position) {
        DataModel.CategoriesBean.ProductsBean productsEntity = mProductsEntities.get(position);

        holder.txtProductDesc.setText(productsEntity.getName());
        if (productsEntity.getTax() != null) {
            holder.txtPrice.setText(productsEntity.getTax().getName()+" "+String.valueOf(productsEntity.getTax().getValue()));
        }

        if(productsEntity.getVariants() != null && productsEntity.getVariants().size() > 0 ){
            holder.rlVarient.setVisibility(View.VISIBLE);
            holder.rlVarient.setLayoutManager(new LinearLayoutManager(mProductListView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            ProductSizeAdapter sizeAdapter = new ProductSizeAdapter(productsEntity.getVariants());
            holder.rlVarient.setAdapter(sizeAdapter);
        } else {
            holder.rlVarient.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mProductsEntities != null ? mProductsEntities.size() : 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtProductDesc)
        TextView txtProductDesc;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.rlVarient)
        RecyclerView rlVarient;

        ItemViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}