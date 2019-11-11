package com.linxf.base.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.linxf.base.R;

/**
 * 类说明
 * Created by Linxf on 2019/4/12.
 */

public class BottomDialog extends Dialog {
    public BottomDialog(@NonNull Context context) {
        super(context, R.style.alert_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
