package com.linxf.lintestdemo;

import android.widget.TextView;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.dagger.MainComponent;
import com.linxf.lintestdemo.dagger.Student;

import javax.inject.Inject;

import dagger.internal.DaggerCollections;

/**
 * dagger测试activity
 * Created by Linxf on 2019/5/24.
 */

public class DaggerActivity extends BaseActivity {
    @Inject
    Student mStudent;  // 注入Studnet的实例
    private TextView textTxV;


    @Override
    public int getLayoutId() {

        return R.layout.activity_dagger;
    }

    @Override
    public void initView() {
        LogUtil.error("1232");

//        LogUtil.error("数据:" + mStudent.showMessage());

        textTxV =  findViewById(R.id.textTxV);
//        textTxV.setText(mStudent.showMessage());
    }

    @Override
    public void initData() {

    }
}
