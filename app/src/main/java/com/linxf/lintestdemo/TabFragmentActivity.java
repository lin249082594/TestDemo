package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;

import com.linxf.base.BaseActivity;
import com.linxf.base.controller.ViewPageFragmentController;
import com.linxf.base.model.FragmentTab;
import com.linxf.lintestdemo.databinding.ActivityFragmentDemoBinding;
import com.linxf.lintestdemo.fragment.TabFragment;
import com.linxf.lintestdemo.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class TabFragmentActivity  extends BaseActivity<ViewPageFragmentController>{
    private ActivityFragmentDemoBinding activityFragmentDemoBinding;
    private List<FragmentTab> list = new ArrayList<>();
    private int whiteColor;
    private int selectedColor;
    @Override
    public int getLayoutId() {
        initContorller(ViewPageFragmentController.class);
        activityFragmentDemoBinding = DataBindingUtil.setContentView(this,R.layout.activity_fragment_demo);
        return 0;
    }

    @Override
    public void initView() {
        whiteColor = ContextCompat.getColor(this,R.color.white);
        selectedColor = ContextCompat.getColor(this,R.color.c_c1d2f0);

        FragmentTab item = new FragmentTab();
        item.setTabIndex(0);
        item.setClazz(TabFragment.class);
        item.setTabName("tab0");

        list.add(item);
        item = new FragmentTab();
        item.setTabIndex(1);
        item.setClazz(ViewPagerFragment.class);
        item.setTabName("tab1");

        list.add(item);


        getController().initViewPagerFragment(getSupportFragmentManager(),activityFragmentDemoBinding.viewPager,activityFragmentDemoBinding.tabs,list);
        getController().setupTabs(whiteColor,selectedColor,whiteColor,true);
    }

    @Override
    public void initData() {

    }
}
