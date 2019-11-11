package com.linxf.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linxf.base.application.MainHandler;
import com.linxf.base.application.RxBus;
import com.linxf.base.controller.BaseController;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.StringUtil;

import java.lang.reflect.Constructor;



/**
 * 类说明
 * Created by Linxf on 2019/2/19.
 */

public abstract class BaseFragment<T extends BaseController>  extends Fragment {
    protected T      controller;

    public View mRootView;
    private Context context;

    private String param;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null){
            int layout = getLayoutId(inflater,container);

            if(layout > 0)
                mRootView = inflater.inflate(layout,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
//        RxBus.get().register(this);
        initView();

        initData();
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        RxBus.get().unregister(this);
    }

    @SuppressWarnings("unchecked")
    public void initContorller(Class<? extends BaseController> clz)
    {
        try
        {

//            Class<? extends BaseController> clz = (Class<? extends BaseController>) Class.forName(className);
            Constructor<? extends BaseController> constructor = clz.getConstructor(Activity.class);
            controller = (T) constructor.newInstance(BaseFragment.this.getContext());
        } catch (Exception e) {
            LogUtil.error("初始化controller异常:" + e.toString());
        }
    }
    protected T getController()
    {
        return controller;
    }

    @SuppressWarnings("unchecked")
    public <E extends View> E findView( int id) {

        return (E) mRootView.findViewById(id);
    }
    public abstract int getLayoutId(LayoutInflater inflater,ViewGroup container);
    public abstract void initView();
    public abstract void initData();




    /**
     *
     * @param jump
     * @param data
     * @param requestCode
     */
    public void jumpForResult(Class<?> jump, Bundle data,int requestCode){
        Intent intent = new Intent(getActivity(), jump);
        if (null != data && data.size() > 0) {
            intent.putExtras(data);
        }

        startActivityForResult(intent,requestCode);
    }


    /**
     * 界面跳转
     * @param jump
     * @param data
     */
    public void jump(Class<?> jump, Bundle data) {
        if(jump == null)
            return;
        Intent intent = new Intent(getContext(), jump);
        if (null != data && data.size() > 0) {
            intent.putExtras(data);
        }
        if( !getActivity().isFinishing()){
            startActivity(intent);
        }

    }
    public void jump(String jumpPath, Bundle data) {
        if(StringUtil.isEmpty(jumpPath))return;
       try {
           Intent intent = new Intent(getContext(), Class.forName(jumpPath));
           if (null != data && data.size() > 0) {
               intent.putExtras(data);
           }
           if(getActivity() != null && !getActivity().isFinishing()){
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
                    Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    // 跳转到当前应用的设置界面
    public void goToAppSetting(int taskId) {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",getContext().getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, taskId);
    }

//    //permission权限回调
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        LogUtil.error("onRequestPermissionsResult freag");
//        PermissionGen.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
//
//    }
}
