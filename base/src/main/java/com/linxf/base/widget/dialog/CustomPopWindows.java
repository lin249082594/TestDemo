package com.linxf.base.widget.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.linxf.base.R;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.VUtils;

/**
 * 类说明
 * Created by Linxf on 2019/4/15.
 */

public class CustomPopWindows extends PopupWindow {
    private Context context;
    View view;
    public CustomPopWindows(Context context){
        this.context = context;
        init();
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_windows, null);
        setContentView( view);

        // 设置动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //防止虚拟键挡住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置弹出窗体的 宽，高
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置可触
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x0000000);
        this.setBackgroundDrawable(dw);

        // 单击弹出窗以外处 关闭弹出窗
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int height = view.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void show(View view){
//        if (Build.VERSION.SDK_INT < 24) {
//            showAsDropDown(view);
//        } else {
//            int[] location = new int[2];
//            view.getLocationOnScreen(location);
//            int x = location[0];
//            int y = location[1];
//            LogUtil.error("x:" + x + "  y:" + y + "   " +  view.getHeight());
//           showAtLocation(view, Gravity.NO_GRAVITY, x ,y + view.getHeight());
//        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        LogUtil.error("x:" + x + "  y:" + y + "   " +  view.getHeight());
        showAtLocation(view, Gravity.NO_GRAVITY, VUtils.getScreenWidth(context) - VUtils.dip2px(context,60),VUtils.dip2px(context,50));
    }

}
