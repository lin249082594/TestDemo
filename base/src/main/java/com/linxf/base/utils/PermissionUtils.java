package com.linxf.base.utils;
/*
    Copyright 2015 ChangXing kingh.cha@gmail.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;


import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;


public class PermissionUtils {
    /**
     * 通讯录权限
     */
    public static final int  REQUEST_CONTACT = 1111;
    /**
     * sd卡权限
     */
    public static final int  REQUEST_SDCARD= 1112;
    /**
     * 地理位置权限
     */
    public static final int  REQUEST_LOCATION= 1113;
    /**
     * 摄像头权限
     */
    public static final int  REQUEST_CAMERA= 1114;
    /**
     * 录音
     */
    public static final int  REQUEST_AUDIO= 1115;

    /**
     * media权限
     */
    public static final int  REQUEST_MEDIA= 1116;
    /**
     * 通知权限
     */
    public static final int  REQUEST_NOTIFICATION= 1117;

    public static final int  REQUEST_HOUTAI = 1118;
    /**
     * 读取手机号码
     */
    public static final int  REQUEST_READ_PHONE = 1119;
    /**
     * 安装应用权限
     */
    public static final int  REQUEST_INSTALL_APP = 1120;
    /**
     * 摄像头具体权限
     */
    public static final String[] PERMISSIONS_CAMERA = new String[]{Manifest.permission.CAMERA};

    public static final String[] PERMISSIONS_READ_PHONE = new String[]{Manifest.permission.READ_PHONE_STATE};

    /**
     * 安装应用权限
     */
    public static final String[] PERMISSIONS_INSTALL_APP = new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES};

    /**
     * 通讯录具体权限
     */
    public static final String[] PERMISSIONS_CONTACT = new String[]{Manifest.permission.READ_CONTACTS};
    /**
     * sd卡具体权限
     */
    public static final String[] PERMISSIONS_SDCARD= new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    /**
     * location具体权限
     */
    public static final String[] PERMISSIONS_LOCATION = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

    /**
     * 录音
     */
    public static final String[] PERMISSION_AUDIO= new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS};


    public static final String[] PERMISSION_MEDIA= new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.CAMERA};




    /**
     * //检测权限
     * 返回个数大于0表示有权限没同意
     * @param rxPermissions
     * @param permissions
     * @return
     */
    public static String[] checkPermission(RxPermissions rxPermissions, String[] permissions){
        List<String> data = new ArrayList<>();//存储未申请的权限
        for (String permission : permissions) {
            if(!rxPermissions.isGranted(permission))
                data.add(permission);
        }
        return data.toArray(new String[data.size()]);

    }

    /**
     * 展示设置权限的dialog
     */
    public static void showSetPermissionDialog(AlertDialog dialog, Context context, String text, DialogInterface.OnClickListener posListener){
        //创建对话框创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置标题
        builder.setTitle("申请权限");
        //设置正文
        builder.setMessage(text +  "\n\n请在-应用设置-权限管理-中，允许微脉使用该权限");

        //添加确定按钮点击事件
        builder.setPositiveButton("确定", posListener);

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });
        builder.setCancelable(false);
        //使用构建器创建出对话框对象
        dialog = builder.create();
        dialog.show();//显示对话框
    }



}
