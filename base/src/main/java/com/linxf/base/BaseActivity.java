package com.linxf.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.linxf.base.application.MainHandler;
import com.linxf.base.application.RxBus;
import com.linxf.base.constant.ActivityAnim;
import com.linxf.base.controller.BaseController;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;



/**
 * 类说明
 * Created by Linxf on 2019/3/27.
 */

public abstract  class BaseActivity<T extends BaseController> extends AppCompatActivity implements View.OnClickListener{
    protected T      controller;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.getBaseApplication().addActivity(this);
        //如果用databinding  不设置contentView  返回-1
        int layout = getLayoutId();
        if(layout > 0)
            setContentView(layout);

//        RxBus.get().register(this);
        initView();
        initData();
    }

    @SuppressWarnings("unchecked")
    public void initContorller(Class<? extends BaseController> clz)
    {
        try
        {

//            Class<? extends BaseController> clz = (Class<? extends BaseController>) Class.forName(className);
            Constructor<? extends BaseController> constructor = clz.getConstructor(Activity.class);
            controller = (T) constructor.newInstance(BaseActivity.this);
        } catch (Exception e) {
            LogUtil.error("初始化controller异常:" + e.toString());
        }


    }

    protected T getController()
    {
        return controller;
    }

    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxBus.get().unregister(this);
    }

    /**
     * 界面跳转
     * @param jump
     * @param data
     */
    public void jump(Class<?> jump, Bundle data) {
        if(jump == null)
            return;
        Intent intent = new Intent(this, jump);
        if (null != data && data.size() > 0) {

            intent.putExtras(data);
        }
        if(!isFinishing()){
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View v) {

    }

    public void jumpWithAnim(Class<?> jump, Bundle data, int anim) {
        jump(jump,data);
        BaseApplication.getBaseApplication().addAnim(jump.getSimpleName(),anim);
        startActivityAnim(anim);
    }

    public void jumpWithAnimForResult(Class<?> jump, Bundle data,int anim,int requestCode) {
        jumpForResult(jump,data,requestCode);
        BaseApplication.getBaseApplication().addAnim(jump.getSimpleName(),anim);
        startActivityAnim(anim);
    }

    public void jumpForResult(Class<?> jump, Bundle data,int requestCode) {
        Intent intent = new Intent(this, jump);
        if (null != data && data.size() > 0) {
            intent.putExtras(data);
        }
        if(!isFinishing()){
            startActivityForResult(intent,requestCode);
        }

    }

    public void jump(Class<?> jump, Bundle data,int flag) {
        Intent intent = new Intent(this, jump);
        if (null != data && data.size() > 0) {
            intent.putExtras(data);
        }
        intent.addFlags(flag);
        if(!isFinishing()){
            startActivity(intent);
        }

    }

    public void startActivityAnim(int type){

        switch (ActivityAnim.getAnim(BaseApplication.getBaseApplication().getAnim(getClass().getSimpleName()))){
            case TOP_TO_BOTTOM:
                overridePendingTransition(R.anim.fade_bottom_top,R.anim.fade_top_minus);
                break;

        }

    }

    public  void outActivityAnim(){

        switch (ActivityAnim.getAnim(BaseApplication.getBaseApplication().getAnim(getClass().getSimpleName()))){
            case TOP_TO_BOTTOM:
                overridePendingTransition(R.anim.fade_minus_top,R.anim.fade_top_bottom);
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
//			System.out.println("rrrr:" + ShopApplication.getInstance().getAnim(getClass().getSimpleName()));
//			overridePendingTransition(R.anim.fade_minus_top,R.anim.fade_top_bottom);
            outActivityAnim();

            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void jump(String jumpPath, Bundle data) {
        if(StringUtil.isEmpty(jumpPath))return;
        try {
            Intent intent = new Intent(this, Class.forName(jumpPath));
            if (null != data && data.size() > 0) {
                intent.putExtras(data);
            }
            if(isFinishing()){
                startActivity(intent);
            }
        } catch (Exception e){

        }

    }

    public void showToast(final String text){
        if(StringUtil.isNotEmpty(text)){
            MainHandler.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this,text,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    // 跳转到当前应用的设置界面
    public void goToAppSetting(int taskId) {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, taskId);
    }

//    //权限回调
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
//        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//    }
}

