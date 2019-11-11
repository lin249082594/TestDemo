package com.linxf.base.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.linxf.base.R;

/**
 * 类说明
 * Created by Linxf on 2019/4/15.
 */

public class BottomViewDialog extends Dialog {
    public BottomViewDialog(@NonNull Context context) {
        super(context, R.style.alert_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottomview,null);
        setContentView(view);

        TextView textView = view.findViewById(R.id.textView);
        textView.setText("底部dialog");

        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
//
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        getWindow().setAttributes(p); //设置生
    }
}
