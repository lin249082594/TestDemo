package com.linxf.lintestdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.linxf.base.PreviewPhotoActivity;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.StringUtil;
import com.linxf.base.widget.MultiImageView;
import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.PicItem;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/8/8.
 */

public class PicAdapter extends CommonAdapter<PicItem> {

    public PicAdapter(Activity context, int layoutId, List<PicItem> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(int position, ViewHolder holder, PicItem picItem) {
        MultiImageView multiImageView = holder.findView(R.id.multiImageView);

        if(StringUtil.isNotEmpty(picItem.getPic())){
            multiImageView.setVisibility(View.VISIBLE);
            multiImageView.setList(StringUtil.convertStrToList(picItem.getPic()));
        } else {
            multiImageView.setVisibility(View.GONE);
        }
        multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, int position) {
                PreviewPhotoActivity.jumpToPreviewPhoto(getmContext(),view,position,StringUtil.convertStrToList(picItem.getPic()));
            }
        });

    }
}
