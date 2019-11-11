package com.linxf.lintestdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.ImageLoader;
import com.linxf.base.utils.VUtils;
import com.linxf.base.widget.recycleview.RechcleStraggeredDivider;
import com.linxf.lintestdemo.adapter.TestGoodsAdapter;
import com.linxf.lintestdemo.adapter.TestViewTypeAdapter;
import com.linxf.lintestdemo.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 瀑布流布局
 * Created by Linxf on 2019/5/8.
 */

public class RecycleStaggeredActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    //    private LinearLayoutManager linearLayoutManager;
    private TestViewTypeAdapter bdAdapter;
    private TestGoodsAdapter goodsAdapter;
    private SparseArray<Integer> mLayouts = new SparseArray<>();
    private List<Goods> list = new ArrayList<>();
    private List<Goods> mList = new ArrayList<>();
    private ImageLoader imageLoader;
    Handler handler;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    public void initData() {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        Goods item = new Goods();
        item.setName("131212");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i2/2453941017/O1CN01N1Pjko1JNpJOuPdqO_!!0-item_pic.jpg");
        item.setViewType(0);
        list.add(item);
        item = new Goods();
        item.setName("ttt");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i4/3851162713/O1CN01203i6d1Vub2QDUhFk_!!0-item_pic.jpg");
        item.setViewType(1);
        list.add(item);
        item = new Goods();
        item.setName("rrrr");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i3/2326583143/TB2NqjeXy6guuRjy1XdXXaAwpXa_!!2326583143-0-item_pic.jpg");
        item.setViewType(0);
        list.add(item);
        item = new Goods();
        item.setName("we");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i4/791202190/O1CN01eSvW4A1S33tf03tsz_!!0-item_pic.jpg");
        item.setViewType(1);
        list.add(item);
        item = new Goods();
        item.setName("qfewq");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i1/1040955117/TB207V8J1GSBuNjSspbXXciipXa_!!1040955117-0-item_pic.jpg");
        item.setViewType(0);
        list.add(item);
        item = new Goods();
        item.setName("qfewq");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i1/1040955117/TB207V8J1GSBuNjSspbXXciipXa_!!1040955117-0-item_pic.jpg");
        item.setViewType(1);
        list.add(item);

        mLayouts.put(0,R.layout.item_goods);
        mLayouts.put(1,R.layout.item_goods_m);
        imageLoader = new ImageLoader(this);

        int pad = VUtils.dip2px(this,10);
        int width = (VUtils.getScreenWidth(this) - pad)/2;
        int height = width + VUtils.dip2px(this,100);

        bdAdapter = new TestViewTypeAdapter(this,mLayouts,list,imageLoader);




        recyclerView.setAdapter(bdAdapter);
        recyclerView.addItemDecoration(new RechcleStraggeredDivider(pad, ContextCompat.getColor(this,R.color.white),0));

//        recyclerView.addItemDecoration(mDecoration = new SuspensionDecoration(this, mSourceDatas)
//                .setmTitleHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics()))
//                .setColorTitleBg(ContextCompat.getColor(this,R.color.white))
//                .setTitleFontSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()))
//                .setColorTitleFont(ContextCompat.getColor(this,R.color.colorAccent))
//                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount() - mHeaderDatas.size()));


        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mList.addAll(list);
                bdAdapter.notifyDataSetChanged();
            }
        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(new Message());
            }
        },1000);

    }
}
