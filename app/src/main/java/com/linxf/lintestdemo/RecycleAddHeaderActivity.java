package com.linxf.lintestdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.VUtils;
import com.linxf.base.widget.GridViewForScrollView;
import com.linxf.base.widget.recycleview.HeaderRecyclerAndFooterWrapperAdapter;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.base.widget.recycleview.RecycleGridDivider;
import com.linxf.base.widget.recycleview.ViewHolder;
import com.linxf.lintestdemo.adapter.TestAdapter;
import com.linxf.lintestdemo.adapter.TestGoodsAdapter;
import com.linxf.lintestdemo.adapter.TestGoodsFooterAdapter;
import com.linxf.lintestdemo.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/4/4.
 */

public class RecycleAddHeaderActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager mManager;
    //    private LinearLayoutManager linearLayoutManager;
    private TestAdapter bdAdapter;
    private TestGoodsAdapter goodsAdapter;
    private TestGoodsFooterAdapter goodsFooterAdapter;

    private HeaderRecyclerAndFooterWrapperAdapter headerAdapter;
    private List<Goods> list = new ArrayList<>();
    private List<Goods> mList = new ArrayList<>();
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
        mManager = new GridLayoutManager(this,2);
//        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mManager);
        Goods item = new Goods();
        item.setName("131212");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i2/2453941017/O1CN01N1Pjko1JNpJOuPdqO_!!0-item_pic.jpg");
        list.add(item);
        item = new Goods();
        item.setName("ttt");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i4/3851162713/O1CN01203i6d1Vub2QDUhFk_!!0-item_pic.jpg");
        list.add(item);
        item = new Goods();
        item.setName("rrrr");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i3/2326583143/TB2NqjeXy6guuRjy1XdXXaAwpXa_!!2326583143-0-item_pic.jpg");
        list.add(item);
        item = new Goods();
        item.setName("we");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i4/791202190/O1CN01eSvW4A1S33tf03tsz_!!0-item_pic.jpg");
        list.add(item);
        item = new Goods();
        item.setName("qfewq");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i1/1040955117/TB207V8J1GSBuNjSspbXXciipXa_!!1040955117-0-item_pic.jpg");
        list.add(item);
        item = new Goods();
        item.setName("qfewq");
        item.setLogo("https://img.alicdn.com/bao/uploaded/i1/1040955117/TB207V8J1GSBuNjSspbXXciipXa_!!1040955117-0-item_pic.jpg");
        list.add(item);



        final int pad = VUtils.dip2px(this,10);
        int width = (VUtils.getScreenWidth(this) - pad)/2;
        int height = width + VUtils.dip2px(this,100);

        bdAdapter = new TestAdapter(this,R.layout.item_goods,list,width,height);

        headerAdapter = new HeaderRecyclerAndFooterWrapperAdapter(bdAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                switch (layoutId){
                    case R.layout.item_header:
                        GridViewForScrollView gridView = holder.findView(R.id.mGridView);
                        goodsAdapter = new TestGoodsAdapter(RecycleAddHeaderActivity.this,mList);
//                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
//                        gridView.setLayoutManager(gridLayoutManager);
                        gridView.setAdapter(goodsAdapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                showToast(mList.get(position).getName());
                            }
                        });
                        break;
                }
            }
        };


        headerAdapter.setHeaderView(0, R.layout.item_header,mList);

        //添加底部布局
        View view = LayoutInflater.from(this).inflate(R.layout.item_footer,null);
        GridViewForScrollView gridView = view.findViewById(R.id.mGridView);
        goodsFooterAdapter = new TestGoodsFooterAdapter(RecycleAddHeaderActivity.this,mList);
        gridView.setAdapter(goodsFooterAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(mList.get(position).getName());
            }
        });
        headerAdapter.addFooterView(gridView);

        recyclerView.setAdapter(headerAdapter);
        recyclerView.addItemDecoration(new RecycleGridDivider(pad, ContextCompat.getColor(this,R.color.white),1));
        bdAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                showToast(list.get(position).getName());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });


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
                mList.remove(0);
                headerAdapter.notifyDataSetChanged();
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
