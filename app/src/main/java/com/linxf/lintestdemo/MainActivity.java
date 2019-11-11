package com.linxf.lintestdemo;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.linxf.base.BaseActivity;
import com.linxf.base.controller.TabFragmentController;
import com.linxf.base.model.FragmentTab;
import com.linxf.base.utils.BottomNavigationViewHelper;
import com.linxf.lintestdemo.databinding.ActivityMainBinding;
import com.linxf.lintestdemo.fragment.ActivityFragment;
import com.linxf.lintestdemo.fragment.CaseFragment;
import com.linxf.lintestdemo.fragment.WidgetFragment;
import com.linxf.lintestdemo.uentity.MainEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<TabFragmentController> {
    ActivityMainBinding binding;
    MainEntity mainEntity;
    private List<FragmentTab> fragmentTabList = new ArrayList<>();
    @Override
    public int getLayoutId() {
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initContorller(TabFragmentController.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainEntity = new MainEntity();
        binding.setMainEntity(mainEntity);
        return -1;
    }

    @Override
    public void initView() {

        BottomNavigationViewHelper.disableShiftMode(MainActivity.this,binding.navigation);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //如果用图片 则需要
//        binding.navigation.setItemIconTintList(null);
    }

    @Override
    public void initData() {
        FragmentTab fragmentTab = new FragmentTab();
        fragmentTab.setTabIndex(0);
        fragmentTab.setMenuId(R.id.tab_navigation_home);
        fragmentTab.setClazz(ActivityFragment.class);
        fragmentTabList.add(fragmentTab);
        fragmentTab = new FragmentTab();
        fragmentTab.setTabIndex(1);
        fragmentTab.setMenuId(R.id.tab_navigation_widget);
        fragmentTab.setClazz(WidgetFragment.class);
        fragmentTabList.add(fragmentTab);
        fragmentTab = new FragmentTab();
        fragmentTab.setTabIndex(2);
        fragmentTab.setClazz(CaseFragment.class);
        fragmentTab.setMenuId(R.id.tab_navigation_case);
        fragmentTabList.add(fragmentTab);
        getController().initFragment(getSupportFragmentManager(),R.id.content,fragmentTabList);
        getController().setTabSelecion(0);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            getController().setTabSelecion(getController().getPosByMenuId(item.getItemId()));

            return true;
        }
    };


    // fragment_main hide show会有重影
    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }



}
