package com.linxf.lintestdemo.fragment;

import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.linxf.base.LazyBaseFragment;
import com.linxf.base.controller.ViewPageFragmentController;
import com.linxf.base.model.FragmentTab;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.databinding.FragmentViewpagefragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class ViewPagerFragment extends LazyBaseFragment<ViewPageFragmentController> {
    private FragmentViewpagefragmentBinding binding;
    private List<FragmentTab> fragmentTabList = new ArrayList<>();
    private int whiteColor;
    private int selectedColor;

    @Override
    public int getLayoutId(LayoutInflater inflater,ViewGroup container) {
        initContorller(ViewPageFragmentController.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_viewpagefragment,container,false);
        mRootView = binding.getRoot();
        return 0;
    }

    @Override
    public void initView() {
        whiteColor = ContextCompat.getColor(getContext(),R.color.white);
        selectedColor = ContextCompat.getColor(getContext(),R.color.c_c1d2f0);

        for(int i=0;i<5;i++){
            FragmentTab item = new FragmentTab();
            item.setTabIndex(i);
            item.setClazz(SubTagFragment.class);
            item.setTabName("tab" + i);
            item.setParam("fragment" + i);
            fragmentTabList.add(item);
        }

        getController().initViewPagerFragment(getChildFragmentManager(),binding.viewPager,binding.tabs,fragmentTabList);

        getController().setupTabs(whiteColor,selectedColor,whiteColor,false);
    }
    @Override
    public void initLazyData() {

    }
}
