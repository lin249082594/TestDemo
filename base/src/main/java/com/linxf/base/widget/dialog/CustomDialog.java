package com.linxf.base.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.linxf.base.R;

/**
 * 只支持标题 跟内容
 * Created by Linxf on 2019/4/12.
 */

public class CustomDialog extends AlertDialog {
    private String title;
    private String content;

    private View.OnClickListener sureOnclik;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, String title, String content, View.OnClickListener  sureOnclik) {
       this(context);
        this.title = title;
        this.content = content;
        this.sureOnclik = sureOnclik;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        ((TextView)findViewById(R.id.titleTxV)).setText(title);
        ((TextView)findViewById(R.id.contentTxV)).setText(content);
        findViewById(R.id.sureTxV).setOnClickListener(sureOnclik);
        findViewById(R.id.cancelTxV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setContent(String title,String content){
        this.title = title;
        this.content = content;
    }


}
