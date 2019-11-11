package com.linxf.lintestdemo.aac;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.R;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class AacTestActivity extends BaseActivity implements LifecycleObserver {
    private EditText strNameEdV;
    private EditText strAgeEdV;
    private EditText strStuNoEdV;
    private EditText strGradeEdV;
    private Button setBtn;
    private StuViewModel stuViewModel;
    @Override
    public int getLayoutId() {
        return R.layout.activity_aac_test;
    }

    @Override
    public void initView() {
        getLifecycle().addObserver(this);
        setBtn = findViewById(R.id.setBtn);
        strNameEdV = findViewById(R.id.strNameEdV);
        strAgeEdV = findViewById(R.id.strAgeEdV);
        strStuNoEdV = findViewById(R.id.strStuNoEdV);
        strGradeEdV = findViewById(R.id.strGradeEdV);

        stuViewModel = ViewModelProviders.of(this).get(StuViewModel.class);





        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<StUser> data = stuViewModel.getData().getValue();

                StUser stUser = new StUser();
                stUser.setName(strNameEdV.getText().toString());
                stUser.setAge(strAgeEdV.getText().toString());
                stUser.setStuNo(strStuNoEdV.getText().toString());
                stUser.setGrade(strGradeEdV.getText().toString());

                stuViewModel.addData(stUser);


            }
        });
    }

    @Override
    public void initData() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void setStart(){
        LogUtil.error("create设置");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void setResume(){
        LogUtil.error("onResume设置");
    }

}
