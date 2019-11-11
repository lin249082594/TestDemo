package com.linxf.base.controller;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.linxf.base.BaseFragment;
import com.linxf.base.model.FragmentTab;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/4.
 */

public class TabFragmentController extends BaseController {

    public List<FragmentTab> fragmentTabList = new ArrayList<>();
    private BaseFragment[] fragments = null;
    private int contentId;
    private FragmentManager fragmentManager;


    public TabFragmentController(Activity context) {
        super(context);
    }



    public void initFragment(FragmentManager fragmentManager, int contentId,List<FragmentTab> list){

        if(fragmentManager == null || StringUtil.isEmpty(list) || contentId < 0)
            return;
        fragmentTabList.addAll(list);
        this.fragmentManager = fragmentManager;

        fragments = new BaseFragment[fragmentTabList.size()];
        this.contentId = contentId;

    }

    /**
     * 设置tab选中
     * @param pos
     */
    public void setTabSelecion(int pos){
        if(pos<0|| pos>=fragments.length)
            return;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        if(fragments!=null && pos<fragments.length){
            if(fragments[pos] == null){

                BaseFragment fragment = createFragment(pos);
                if(fragment != null){
                    transaction.add(contentId,fragment);
                    fragments[pos] =fragment;
                }

            } else {

                transaction.show(fragments[pos]);
            }

        }
        transaction.commit();
    }

    public int getPosByMenuId(int menuId){
        if(StringUtil.isEmpty(fragmentTabList) || menuId <= 0)
            return -1;
        for(int i=0;i<fragmentTabList.size();i++){
            if(fragmentTabList.get(i).getMenuId() == menuId){
                return i;
            }
        }
        return -1;
    }

    /**
     * 隐藏tab选中
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction){
        if(fragments == null || fragments.length == 0)
            return;
        for(BaseFragment fragment:fragments){
            if(fragment != null)
                transaction.hide(fragment);
        }


    }

    private BaseFragment createFragment(int pos){
        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) fragmentTabList.get(pos).getClazz().newInstance();
            fragment.setParam(fragmentTabList.get(pos).getParam());
        } catch (Exception e){

        }
        return fragment;
    }
}
