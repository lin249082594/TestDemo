package com.linxf.base.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;

import com.linxf.base.BaseFragment;
import com.linxf.base.model.FragmentTab;
import com.linxf.base.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/3/14.
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    protected final BaseFragment[] fragments;

    protected final Context context;

    private List<FragmentTab> tabs = new ArrayList<FragmentTab>();
    public int getCacheCount() {
        return tabs.size();
    }

    public ViewPagerFragmentAdapter(FragmentManager fm, Context context, ViewPager pager, List<FragmentTab> tabs) {
        super(fm);

        fragments = new BaseFragment[tabs.size()];
        this.context = context;

        this.tabs = tabs;
        init(fm);
    }

    private void init(FragmentManager fm) {
        if(tabs == null || tabs.size() == 0){
            return;
        }
        for (FragmentTab tab : tabs) {
            try {
                BaseFragment fragment = null;

                List<Fragment> fs = fm.getFragments();
                if (fs != null) {
                    for (Fragment f : fs) {
                        if (f.getClass() == tab.getClazz()) {
                            fragment = (BaseFragment) f;
                            break;
                        }
                    }
                }

                if (fragment == null) {
                    fragment = (BaseFragment) tab.getClazz().newInstance();
                }
                // TODO
                fragment.setParam(tab.getParam());

                fragments[tab.getTabIndex()] = fragment;
            } catch (Exception e) {

            }
        }
    }



    @Override
    public BaseFragment getItem(int pos) {
        BaseFragment fragment = fragments[pos];


        return fragment;
    }

    public BaseFragment[] getFragments() {
        return fragments;
    }

    public BaseFragment getFragment(int index){
        try {
            if(fragments != null && fragments.length>0 && fragments.length > index){
                return fragments[index];
            }
        }catch (Exception e){

        }
        return null;
    }



    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        FragmentTab tab = tabs.get(position);
        String resName = tab != null ? tab.getTabName() : "";
        return StringUtil.isNotEmpty(resName) ? resName:"";
    }
}

