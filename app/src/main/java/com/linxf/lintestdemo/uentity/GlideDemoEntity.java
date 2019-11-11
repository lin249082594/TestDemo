package com.linxf.lintestdemo.uentity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import com.linxf.lintestdemo.BR;

/**
 * 类说明
 * Created by Linxf on 2019/4/23.
 */

public class GlideDemoEntity extends BaseObservable {
    private String textStr1;
    private String textStr2;
    private String textStr3;
    private String textStr4;
    private String textStr5;

    @Bindable
    public String getTextStr1() {
        return textStr1;
    }

    public void setTextStr1(String textStr1) {
        this.textStr1 = textStr1;
        notifyPropertyChanged(BR.textStr1);
    }
    @Bindable
    public String getTextStr2() {
        return textStr2;
    }

    public void setTextStr2(String textStr2) {
        this.textStr2 = textStr2;
    }
    @Bindable
    public String getTextStr3() {
        return textStr3;
    }

    public void setTextStr3(String textStr3) {
        this.textStr3 = textStr3;
    }

    @Bindable
    public String getTextStr4() {
        return textStr4;
    }

    public void setTextStr4(String textStr4) {
        this.textStr4 = textStr4;
    }

    @Bindable
    public String getTextStr5() {
        return textStr5;
    }

    public void setTextStr5(String textStr5) {
        this.textStr5 = textStr5;
    }
}
