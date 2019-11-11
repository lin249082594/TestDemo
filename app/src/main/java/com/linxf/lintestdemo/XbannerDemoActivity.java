package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.linxf.base.BaseActivity;
import com.linxf.lintestdemo.databinding.ActivityXbannerDemoBinding;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */
//TODO  具体用法参考 https://github.com/xiaohaibin/XBanner
public class XbannerDemoActivity extends BaseActivity {
    private ActivityXbannerDemoBinding binding;
    private List<String> imageList = new ArrayList<>();
    private List<String> tipList = new ArrayList<>();
    private List<String> flowList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_xbanner_demo);
        return 0;
    }

    @Override
    public void initView() {
        imageList.add("http://www.yedujia.com/content/chanpinfengmian/7e193d0e-d30e-4638-93d0-a63c00fd66ac/r_7e193d0e-d30e-4638-93d0-a63c00fd66ac.jpg");
        imageList.add("http://www.yedujia.com/content/chanpinfengmian/953b0d8e-5384-41f5-828b-a75b00ae96f8/r_953b0d8e-5384-41f5-828b-a75b00ae96f8.jpg");
        imageList.add("http://www.yedujia.com/content/chanpinfengmian/6f1d5260-b190-4aa6-afb0-a6f501069795/r_6f1d5260-b190-4aa6-afb0-a6f501069795.jpg");

        tipList.add("图片1");
        tipList.add("图片2");
        tipList.add("图片3");

        binding.xBanner.setData(imageList,null);
        binding.xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XbannerDemoActivity.this).load(imageList.get(position)).into((ImageView) view);
            }
        });


        binding.xBanner1.setData(imageList,tipList);
        binding.xBanner1.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XbannerDemoActivity.this).load(imageList.get(position)).into((ImageView) view);
            }
        });
        binding.xBanner1.setPageTransformer(Transformer.Accordion);


        binding.xBanner2.setData(imageList,null);
        binding.xBanner2.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XbannerDemoActivity.this).load(imageList.get(position)).into((ImageView) view);
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.xBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.xBanner.stopAutoPlay();
    }
}
