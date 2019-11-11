package com.linxf.lintestdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.linxf.base.BaseFragment;
import com.linxf.base.LazyBaseFragment;
import com.linxf.base.controller.TabFragmentController;
import com.linxf.base.model.FragmentTab;
import com.linxf.base.utils.BottomNavigationViewHelper;
import com.linxf.lintestdemo.R;


import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class TabFragment extends LazyBaseFragment<TabFragmentController> {
    private BottomNavigationView navigation;
    private List<FragmentTab> fragmentTabList = new ArrayList<>();
    @Override
    public int getLayoutId(LayoutInflater inflater,ViewGroup container) {
        initContorller(TabFragmentController.class);

        return R.layout.fragment_tabfragment;
    }

    @Override
    public void initView() {
        navigation = findView(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(getContext(),navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //如果用图片 则需要
//        binding.navigation.setItemIconTintList(null);
    }

    @Override
    public void initLazyData() {

        FragmentTab fragmentTab = new FragmentTab();
        fragmentTab.setTabIndex(0);
        fragmentTab.setMenuId(R.id.subtab_1);
        fragmentTab.setClazz(RefreshFragment.class);
        fragmentTab.setParam("fragment1");
        fragmentTabList.add(fragmentTab);
        fragmentTab = new FragmentTab();
        fragmentTab.setTabIndex(1);
        fragmentTab.setMenuId(R.id.subtab_2);
        fragmentTab.setClazz(PermissionFragment.class);
        fragmentTab.setParam("fragment2" );
        fragmentTabList.add(fragmentTab);
        fragmentTab = new FragmentTab();
        fragmentTab.setTabIndex(2);
        fragmentTab.setMenuId(R.id.subtab_3);
        fragmentTab.setClazz(SubTagFragment.class);
        fragmentTab.setParam("fragment3");
        fragmentTabList.add(fragmentTab);


        getController().initFragment(getChildFragmentManager(),R.id.content,fragmentTabList);

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}
