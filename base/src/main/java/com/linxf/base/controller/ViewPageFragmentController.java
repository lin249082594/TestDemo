package com.linxf.base.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.linxf.base.BaseApplication;
import com.linxf.base.adapter.ViewPagerFragmentAdapter;
import com.linxf.base.model.FragmentTab;
import com.linxf.base.utils.StringUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class ViewPageFragmentController extends BaseController  {

    public ViewPager viewPager;
    public MagicIndicator tabs;
    public ViewPagerFragmentAdapter adapter;
    public List<FragmentTab> list = new ArrayList<>();
    private FragmentManager fm;

    public ViewPageFragmentController(Activity context) {
        super(context);
    }

    public void initViewPagerFragment(FragmentManager fm,ViewPager viewPager,MagicIndicator tabs,List<FragmentTab> list){
        this.viewPager = viewPager;
        this.tabs = tabs;
        this.list = list;
        this.fm = fm;
    }

    /**
     * 设置tab
     * @param normalColor  正常颜色
     * @param selectedColor  选中的颜色
     * @param lineColor   滚动线条颜色
     * @param isAllowed   是否自适应跳转 true 会平分
     */
    public void setupTabs(final int normalColor, final int selectedColor, final int lineColor,boolean isAllowed) {


        CommonNavigator commonNavigator = new CommonNavigator(getmContext());
        commonNavigator.setAdjustMode(isAllowed);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(normalColor);
                colorTransitionPagerTitleView.setSelectedColor(selectedColor);

                colorTransitionPagerTitleView.setText(list.get(index).getTabName());
                colorTransitionPagerTitleView.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 15, getmContext().getResources().getDisplayMetrics()));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
//                if(StringUtil.isEmpty(lineColor)){
//                    indicator.setVisibility(View.GONE);
//                } else {
//                    indicator.setColors(Color.parseColor(lineColor));
//                }
                if(lineColor == 0){
                    indicator.setVisibility(View.GONE);
                } else {
                    indicator.setColors(lineColor);
                }
                return indicator;
            }
        });
        tabs.setNavigator(commonNavigator);
        ViewPagerHelper.bind(tabs, viewPager);

        adapter = new ViewPagerFragmentAdapter(fm, getmContext(), viewPager,list);
        viewPager.setOffscreenPageLimit(adapter.getCacheCount());
        viewPager.setAdapter(adapter);
    }


}
