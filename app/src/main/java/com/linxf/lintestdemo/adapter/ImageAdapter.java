package com.linxf.lintestdemo.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.linxf.base.utils.ImageLoader;
import com.linxf.base.widget.recycleview.CommonAdapter;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.entity.Image;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/23.
 */

public class ImageAdapter extends CommonAdapter<Image> {

    public ImageAdapter(Context context,  List<Image> datas) {
        super(context, R.layout.image, datas);
    }

    @Override
    public void convert(int position, ViewHolder holder, Image image) {
        ImageView imageView = holder.findView(R.id.imageView);
        new ImageLoader(imageView).loadImage(image.getImage(),imageView);
    }
}
