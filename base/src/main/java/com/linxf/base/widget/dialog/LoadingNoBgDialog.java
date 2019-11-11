package com.linxf.base.widget.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.linxf.base.R;


/**
 * 无阴影dialog
 * Created by Linxf on 2019/3/15.
 */

public class LoadingNoBgDialog extends Dialog {
    private boolean cancelAble = true;

       public LoadingNoBgDialog(@NonNull Context context) {
        super(context, R.style.noShadeProgressDialog);
    }
    public LoadingNoBgDialog(@NonNull Context context, boolean cancelAble ) {
        this(context);
        this.cancelAble = cancelAble;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(cancelAble);


        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pregeess,null);
        setContentView(view);





        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        window.setDimAmount(0f);
//
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
//        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        p.gravity = Gravity.CENTER;

        getWindow().setAttributes(p); //设置生
    }




}
