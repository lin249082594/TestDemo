package com.linxf.lintestdemo.uentity;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * 类说明
 * Created by Linxf on 2019/5/10.
 */

public class RxEntity extends BaseMvvm {

    private String text1;

    @Bindable
    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
        notifyPropertyChanged(BR.text1);
    }
}
