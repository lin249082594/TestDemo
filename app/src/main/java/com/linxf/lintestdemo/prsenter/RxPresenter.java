package com.linxf.lintestdemo.prsenter;

import android.util.Log;
import android.view.View;

import com.linxf.base.utils.LogUtil;
import com.linxf.lintestdemo.uentity.BaseMvvm;
import com.linxf.lintestdemo.uentity.RxEntity;

/**
 * 类说明
 * Created by Linxf on 2019/5/10.
 */

public class RxPresenter extends BaseMvvm {

    public void onBtn1Click(View view, RxEntity user){

        user.setText1("23");
    }

}
