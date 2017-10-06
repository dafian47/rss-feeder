package com.dafian.android.rssfeeder.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dafian.android.rssfeeder.R;
import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.util.Helper;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Dafian on 10/6/17
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private Context context;
    private List<ItemEntity> items;

    public MainAdapter(Context context, List<ItemEntity> items) {
        this.context = context;
        this.items = items;
    }

    class MainHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_thumb)
        ImageView ivThumb;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_pub_date)
        TextView tvPubDate;

        MainHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.row_main, parent, false);
        return new MainHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {

        ItemEntity item = items.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvCategory.setText(item.getCategory());
        holder.tvPubDate.setText(Helper.convertDateRss(item.getPublishDate()));

        Picasso.with(context)
                .load(item.getImage())
                .into(holder.ivThumb);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
