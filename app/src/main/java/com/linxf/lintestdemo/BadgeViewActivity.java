package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.view.View;


import com.linxf.base.BaseActivity;
import com.linxf.base.application.MainHandler;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.VUtils;
import com.linxf.lintestdemo.databinding.ActivityBadgeviewBinding;
import com.linxf.lintestdemo.uentity.BadgeAcEntity;

import q.rorbin.badgeview.Badge;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */
//TODO 具体参照 https://github.com/qstumn/BadgeView
public class BadgeViewActivity extends BaseActivity {
    private ActivityBadgeviewBinding binding;
    private BadgeAcEntity badgeAcEntity;

    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_badgeview);
        badgeAcEntity = new BadgeAcEntity();
        badgeAcEntity.setText1("按钮1");
        badgeAcEntity.setText2("按钮2");
        badgeAcEntity.setText3("按钮3");
        badgeAcEntity.setText4("按钮4");
        badgeAcEntity.setText5("更改文字");
    
        binding.setBadgeAcEntity(badgeAcEntity);
        return 0;
    }

    @Override
    public void initView() {



        badgeAcEntity.bd1 = VUtils.setViewNumBadge(this,binding.text1,-1,null,5);
        badgeAcEntity.bd2 = VUtils.setViewNumBadge(this,binding.text2,-1,null,5);
        badgeAcEntity.bd2.setBadgeNumber(-1);
        badgeAcEntity.bd3 = VUtils.setViewTextBadge(this,binding.text3,"3232",null,5);
        badgeAcEntity.bd4 = VUtils.setViewTextBadge(this, binding.text4, "3232", new Badge.OnDragStateChangedListener() {
            @Override
            public void onDragStateChanged(int dragState, Badge badge, View targetView) {

            }
        }, 5);



    }

    @Override
    public void initData() {
        LogUtil.error("12");
    }
}
