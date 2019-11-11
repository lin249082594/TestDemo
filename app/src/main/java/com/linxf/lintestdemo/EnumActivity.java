package com.linxf.lintestdemo;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.entity.EnumUtils;

public class EnumActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {
        LogUtil.error("值:" + EnumUtils.getNetWorkString(EnumUtils.STRING_2G));

    }

    @Override
    public void initData() {

    }
}
