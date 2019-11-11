package com.linxf.lintestdemo.fragment;

import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxf.base.BaseFragment;

import com.linxf.base.application.BusAction;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.AACActivity;
import com.linxf.lintestdemo.CacheOOMActivity;
import com.linxf.lintestdemo.DaggerActivity;
import com.linxf.lintestdemo.GlideTestActivity;
import com.linxf.lintestdemo.GreenDaoActivity;
import com.linxf.lintestdemo.LambdaActivity;
import com.linxf.lintestdemo.LifecycleActivity;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.RetrofitActivity;
import com.linxf.lintestdemo.RoomActivity;
import com.linxf.lintestdemo.RxJavaActivity;
import com.linxf.lintestdemo.aac.AacTestActivity;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * 案例fragment
 * Created by Linxf on 2019/3/27.
 */

public class CaseFragment extends BaseFragment {
    private List<MainUi> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private MainItemAdapter mainItemAdapter;

    @Override
    public int getLayoutId(LayoutInflater inflater,ViewGroup container) {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findView(R.id.rv);

    }

    @Override
    public void initData() {
        MainUi item = new MainUi();
        item.setName("glide加载图片压力测试 防止oom");
        item.setPath(GlideTestActivity.class);
//	cache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
        list.add(item);

        item = new MainUi();
        item.setName("内存泄露");
        item.setPath(CacheOOMActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("后台service 问题 特别是8.0");
        list.add(item);

        item = new MainUi();
        item.setName("Dagger2特别是8.0");
        item.setPath(DaggerActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("数据库操作");
        item.setPath(GreenDaoActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("RxJava");
        item.setPath(RxJavaActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("Retrofit调用网络请求");
        item.setPath(RetrofitActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("Lifecycle");
        item.setPath(LifecycleActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("RxLifecycle");
        item.setPath(RxJavaActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("java8 lambda表达式");
        item.setPath(LambdaActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("Android Architecture Components Lifecycle, LiveData, ViewModel,Room");
        item.setPath(AacTestActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("Room使用");
        item.setPath(RoomActivity.class);
        list.add(item);

        item = new MainUi();
        item.setName("ActivityOptionsCompat  makeSceneTransitionAnimation  切换动画");
        list.add(item);


        mainItemAdapter = new MainItemAdapter(getContext(),list);
        mainItemAdapter.setOnItemClickListener(new OnItemClickListener<MainUi>() {


            @Override
            public void onItemClick(ViewGroup parent, View view, MainUi mainUi, int position) {
                jump(mainUi.getPath(),null);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, MainUi mainUi, int position) {
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mainItemAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));



    }

//    //rx bus接受消息
//    @Subscribe(thread = EventThread.MAIN_THREAD,
//            tags = {@Tag(BusAction.REFRESH_CAST)
//            })
//    public void eat(String food) {
//        showToast(food);
//    }

}
