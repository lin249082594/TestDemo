package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.BlurBitmapUtils;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.ViewSwitchUtils;
import com.linxf.base.widget.cardview.CardScaleHelper;
import com.linxf.lintestdemo.adapter.CardAdapter;
import com.linxf.lintestdemo.databinding.ActivitySpeedrecycleBinding;
import com.linxf.lintestdemo.datas.DatasUtils;
import com.linxf.lintestdemo.entity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * 滑动RecycleActivity
 * Created by Linxf on 2019/4/12.
 */

public class SpeedRecycleActivity extends BaseActivity{
    private ActivitySpeedrecycleBinding binding;
    private CardAdapter cardAdapter;
    private List<Goods> list = new ArrayList<>();
    private CardScaleHelper mCardScaleHelper;
    private int mLastPos = -1;
    private Runnable mBlurRunnable;
    private Bitmap bgBitmap;
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_speedrecycle);
        return 0;
    }

    @Override
    public void initView() {
        list.addAll(DatasUtils.getGoodsList());
        cardAdapter =new CardAdapter(this,list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        binding.speedRV.setLayoutManager(linearLayoutManager);
        binding.speedRV.setAdapter(cardAdapter);


        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(0);
        mCardScaleHelper.attachToRecyclerView(binding.speedRV);

    }

    @Override
    public void initData() {
        binding.speedRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange();
                }
            }
        });

        notifyBackgroundChange();

    }


    private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return;
        mLastPos = mCardScaleHelper.getCurrentItemPos();



        binding.blurView.removeCallbacks(mBlurRunnable);
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
               try {

//                   bgBitmap = Glide.with(SpeedRecycleActivity.this)
//                           .load(list.get(mLastPos).getLogo())
//                           .asBitmap() //必须
//                           .centerCrop()
//                           .into(350,175)
//                           .get();
//                   bgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.empty);

                   handler.sendEmptyMessage(1);
               } catch (Exception e){
                   LogUtil.error("异常:" + e.toString());
               }
            }
        };
        new Thread(mBlurRunnable).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
                if(bgBitmap != null && !bgBitmap.isRecycled()){


                    ViewSwitchUtils.startSwitchBackgroundAnim(binding.blurView, BlurBitmapUtils.blurBitmap(SpeedRecycleActivity.this, bgBitmap, 5));

                }

            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bgBitmap != null){
            bgBitmap.recycle();
        }
    }
}
