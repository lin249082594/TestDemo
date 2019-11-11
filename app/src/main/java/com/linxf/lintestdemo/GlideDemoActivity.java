package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.ImageLoader;
import com.linxf.lintestdemo.databinding.ActivityGlideDemoBinding;
import com.linxf.lintestdemo.uentity.GlideDemoEntity;

/**
 * 类说明
 * Created by Linxf on 2019/4/23.
 */

public class GlideDemoActivity extends BaseActivity {
    private ActivityGlideDemoBinding dataBinging;
    private GlideDemoEntity entity;
    private ImageLoader imageLoader;
    @Override
    public int getLayoutId() {
        dataBinging = DataBindingUtil.setContentView(this,R.layout.activity_glide_demo);
        entity = new GlideDemoEntity();
        dataBinging.setGlideDemoEntity(entity);
        return 0;
    }

    @Override
    public void initView() {
        imageLoader = new ImageLoader(GlideDemoActivity.this);
        entity.setTextStr1("普通加载1");
        entity.setTextStr2("普通加载2");
        entity.setTextStr3("圆角加载");
        entity.setTextStr4("圆形加载");
        entity.setTextStr5("gif图加载");

        String imageStr = "http://img2.imgtn.bdimg.com/it/u=1420136554,650474194&fm=26&gp=0.jpg";
        String gifStr = "http://photocdn.sohu.com/20150721/mp23627612_1437451852870_2.gif";
        imageLoader.loadImage(imageStr,dataBinging.iamgeView1);
        imageLoader.loadImage(imageStr,dataBinging.iamgeView2);
        imageLoader.loadCornerImage(imageStr,5,dataBinging.iamgeView3);
        imageLoader.loadCornerCenterCropImage(imageStr,8,dataBinging.iamgeView4);
        imageLoader.loadCornerPartImage(imageStr,this,5,0,5,0,dataBinging.iamgeView5);
        imageLoader.loadCornerPartCenterCropImage(imageStr,this,5,0,5,0,dataBinging.iamgeView6);
        imageLoader.loadRoundCircleImage(imageStr,this,5,dataBinging.iamgeView7);

        imageLoader.loadImage(gifStr,dataBinging.iamgeView9);
        imageLoader.loadImage(gifStr,dataBinging.iamgeView10);
    }

    @Override
    public void initData() {

    }
}
