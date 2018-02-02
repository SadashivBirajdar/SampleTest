package com.example.sampletest.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sampletest.R;
import com.example.sampletest.model.DataModel;
import com.example.sampletest.view.productList.ProductListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by emb-madhtom on 7/3/17.
 */

public class SortAdapter extends RecyclerView.Adapter<SortAdapter.MyViewHolder> {


    private final ProductListView mView;
    private List<DataModel.RankingsBean> rankingsBeans;

    public SortAdapter(ProductListView view, List<DataModel.RankingsBean> rankings) {
        this.rankingsBeans = rankings;
        this.mView = view;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sort, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String sortDisplayName = rankingsBeans.get(position).getRanking();
        if (TextUtils.isEmpty(sortDisplayName)) {
            holder.rowLayout.setVisibility(View.GONE);
        } else {
            holder.rowLayout.setVisibility(View.VISIBLE);
            holder.name.setText(sortDisplayName);
        }
        if (position == rankingsBeans.size() - 1) {
            holder.viewDivider.setVisibility(View.GONE);
        } else {
            holder.viewDivider.setVisibility(View.VISIBLE);
        }
        /*if (rankingsBeans.get(position).isSelected()) {
            holder.rowLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.secondary_color_35_percent_yellow));
        } else {
            holder.rowLayout.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent));
        }*/
    }

    @Override
    public int getItemCount() {
        return rankingsBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtName)
        TextView name;
        @BindView(R.id.separation_line)
        View viewDivider;
        @BindView(R.id.lin_item_sort)
        LinearLayout rowLayout;


        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mView.applySort(rankingsBeans.get(getAdapterPosition()));
                }
            });
        }

    }
}
