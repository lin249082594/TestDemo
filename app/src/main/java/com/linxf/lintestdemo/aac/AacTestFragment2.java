package com.linxf.lintestdemo.aac;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.linxf.base.BaseFragment;
import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class AacTestFragment2 extends BaseFragment {
    private StuViewModel mModel;
    private Observer<List<String>> mObserver;
    private ListView listView;
    private List<String> list = new ArrayList<>();
    private MAdapter adapter ;

    @Override
    public int getLayoutId(LayoutInflater inflater, ViewGroup container) {
        return R.layout.fragment_aac_test;
    }

    @Override
    public void initView() {
        listView = (ListView) findView(R.id.listView);

        mModel = ViewModelProviders.of((FragmentActivity) getContext()).get(StuViewModel.class);
        adapter = new MAdapter(getContext(),list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mModel.deleteData(position);
            }
        });
    }

    @Override
    public void initData() {
        mObserver = new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> mList) {
                list.clear();
                list.addAll(mList);
                adapter.notifyDataSetChanged();
            }
        };
        mModel.getSwitchMapData().observe(getActivity(),mObserver);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mModel.getSwitchMapData().removeObserver(mObserver);
    }

}
