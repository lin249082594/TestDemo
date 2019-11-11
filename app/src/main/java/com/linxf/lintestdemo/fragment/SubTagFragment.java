package com.linxf.lintestdemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxf.base.BaseFragment;
import com.linxf.lintestdemo.R;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class SubTagFragment extends BaseFragment {
    @Override
    public int getLayoutId(LayoutInflater inflater, ViewGroup container) {
        return R.layout.fragment_sub_tag;
    }

    @Override
    public void initView() {

        ((TextView)findView(R.id.textView)).setText(getParam());
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }
}
