package com.linxf.lintestdemo.aac;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linxf.base.BaseFragment;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.R;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class AACFragment1 extends BaseFragment {

    private ShareViewModel mModel;
    private TextView mValueTv;
    private Observer<String> mObserver;

//    public AACFragment1() {
//        // Required empty public constructor
//    }
//    public static AACFragment1 newInstance(String param1, String param2) {
//        AACFragment1 fragment = new AACFragment1();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Override
    public int getLayoutId(LayoutInflater inflater, ViewGroup container) {
        return R.layout.activity_aac_fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        init();
    }

    public void init() {

        mValueTv = mRootView.findViewById(R.id.value_tve);
        mModel = ViewModelProviders.of((FragmentActivity) getContext()).get(ShareViewModel.class);
        //直接观察viewmodel的livedata，观察数据变化
//        mModel.getData().observe((FragmentActivity) getContext(), new Observer<Data>() {
//            @Override
//            public void onChanged(@Nullable Data data) {
//                String unit = data.getUnit1();
//                mValueTv.setText(data.getNum() + unit);
//            }
//        });

        //观察viewmodel的Transformations.map（） data，可以得到转换的String
        mObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String data) {
                mValueTv.setText(data);
            }
        };
        mModel.getSitchMapData().observe(getActivity(), mObserver);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        //移除observer
        mModel.getSitchMapData().removeObserver(mObserver);
    }

}
