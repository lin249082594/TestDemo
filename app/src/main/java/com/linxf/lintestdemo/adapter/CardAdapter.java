package com.linxf.lintestdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.widget.cardview.CardAdapterHelper;
import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.Goods;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 类说明
 * Created by Linxf on 2019/4/12.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Goods> mList;
    private Context context;
    private CardAdapterHelper mCardAdapterHelper = new CardAdapterHelper();

    public CardAdapter(Context context, List<Goods> mList) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        mCardAdapterHelper.onCreateViewHolder(parent, itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mCardAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());

//        Glide.with(context).load(mList.get(position).getLogo()).centerCrop().into(holder.mImageView);



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.cardImV);
        }

    }
}
