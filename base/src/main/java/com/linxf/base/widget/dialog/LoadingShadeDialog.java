package com.linxf.base.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.linxf.base.R;


/**
 * 只有阴影 dialog
 * Created by Linxf on 2019/3/8.
 */

public class LoadingShadeDialog extends Dialog {
    public LoadingShadeDialog(@NonNull Context context) {
        super(context, R.style.shadeProgressDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pregeess, null);

//        LinearLayout progressbar = view.findViewById(R.id.progressbarLay);
//        progressbar.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.transparent));

        setContentView(view);


        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
//
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
//        p.width = WindowManager.LayoutParams.WRAP_CONTENT;
        p.gravity = Gravity.CENTER;

        getWindow().setAttributes(p); //设置生
    }


}
