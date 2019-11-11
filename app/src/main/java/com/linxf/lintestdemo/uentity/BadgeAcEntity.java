package com.linxf.lintestdemo.uentity;

import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.view.View;


import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.BR;


import q.rorbin.badgeview.Badge;

/**
 * 类说明
 * Created by Linxf on 2019/4/11.
 */

public class BadgeAcEntity extends BaseMvvm {

    public Badge bd1;
    public Badge bd2;
    public Badge bd3;
    public Badge bd4;

    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String text5;



    public void onChangeClick(View view){
        LogUtil.error("点击");
        setText1("更改11");
        setText2("更改22");
        setText3("更改33");
        setText4("更改44");
    }

    @Bindable
    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
        notifyPropertyChanged(BR.text1);
    }

    @Bindable
    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
        notifyPropertyChanged(BR.text2);
    }
    @Bindable
    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
        notifyPropertyChanged(BR.text3);
    }
    @Bindable
    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
        notifyPropertyChanged(BR.text4);
    }

    @Bindable
    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
        notifyPropertyChanged(BR.text5);
    }


}
