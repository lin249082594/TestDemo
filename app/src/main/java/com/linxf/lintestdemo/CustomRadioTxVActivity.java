package com.linxf.lintestdemo;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.widget.CustomRadioTxV;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/8/8.
 */

public class CustomRadioTxVActivity extends BaseActivity {
    private CustomRadioTxV radioTxV;
    private CustomRadioTxV radio2TxV;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_radiotxv;
    }

    @Override
    public void initView() {
        radioTxV = findViewById(R.id.radioTxV);
        radio2TxV = findViewById(R.id.radio2TxV);
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        list.add("按钮1");
        list.add("按钮2");
        list.add("按钮3");
        list.add("按钮4");
        radioTxV.setOnSelectedListener(new CustomRadioTxV.OnTagSelectedListener() {
            @Override
            public void onSelected(int position) {
                LogUtil.error("选中了:" + position);
            }
        });
        radioTxV.setDatas(list,0);


        List<String> list2 = new ArrayList<>();
        list2.add("按钮1");
        list2.add("按钮2");

        radio2TxV.setOnSelectedListener(new CustomRadioTxV.OnTagSelectedListener() {
            @Override
            public void onSelected(int position) {
                LogUtil.error("选中了:" + position);
            }
        });
        radio2TxV.setDatas(list2,0);
    }
}
