package com.linxf.lintestdemo;

import android.view.View;

import com.linxf.base.BaseActivity;

/**
 * 类说明
 * Created by Linxf on 2019/4/28.
 */

public class ToolbarNormalActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_toolbar;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.right:
                showToast("按键");
                break;
        }
    }
}
