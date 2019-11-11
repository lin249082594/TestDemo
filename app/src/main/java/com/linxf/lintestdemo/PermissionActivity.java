package com.linxf.lintestdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.linxf.base.BaseActivity;
import com.linxf.base.utils.PermissionUtils;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.adapter.PermissionAdapter;
import com.linxf.lintestdemo.databinding.FragmentPermissionBinding;
import com.linxf.lintestdemo.entity.PermissionModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * 申请权限
 * Created by Linxf on 2019/4/12.
 */
//TODO  fragment中的用法见 viewpager fragment中
public class PermissionActivity extends BaseActivity {
    private FragmentPermissionBinding binding;
    private List<PermissionModel> list = new ArrayList<>();
    private PermissionAdapter adapter;
    private AlertDialog dialog;
    private RxPermissions rxPermissions;
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.fragment_permission);
        return 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PermissionUtils.REQUEST_CONTACT ){
           if( PermissionUtils.checkPermission(rxPermissions,PermissionUtils.PERMISSIONS_CONTACT).length > 0){
               showToast("拒绝权限");

           } else {
               showToast("权限同意");
           }

        } else if(requestCode == PermissionUtils.REQUEST_CAMERA){
            if( PermissionUtils.checkPermission(rxPermissions,PermissionUtils.PERMISSIONS_CAMERA).length > 0){
                showToast("拒绝权限");
            } else {
                showToast("权限同意");
            }


        } else if(requestCode == PermissionUtils.REQUEST_SDCARD){
            if( PermissionUtils.checkPermission(rxPermissions,PermissionUtils.PERMISSIONS_SDCARD).length > 0){
                showToast("拒绝权限");
            } else {
                showToast("权限同意");
            }

        }
    }

    @Override
    public void initView() {

        rxPermissions=new RxPermissions(this);

        PermissionModel permission = new PermissionModel();
        permission.setName("打电话权限");
        permission.setPermissionCode(PermissionUtils.REQUEST_CONTACT);
        permission.setPermissions(PermissionUtils.PERMISSIONS_CONTACT);
        list.add(permission);
        permission = new PermissionModel();
        permission.setName("相机权限");
        permission.setPermissionCode(PermissionUtils.REQUEST_CAMERA);
        permission.setPermissions(PermissionUtils.PERMISSIONS_CAMERA);
        list.add(permission);
        permission = new PermissionModel();
        permission.setName("存储空间权限");
        permission.setPermissionCode(PermissionUtils.REQUEST_SDCARD);
        permission.setPermissions(PermissionUtils.PERMISSIONS_SDCARD);
        list.add(permission);

        adapter = new PermissionAdapter(this,list);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, final int position) {
//                if(PermissionUtils.checkPermission(PermissionActivity.this,list.get(position).getPermissions()).length == 0){
//                }
                //请求权限
//                    PermissionGen.with(PermissionActivity.this)
//                            .addRequestCode(list.get(position).getPermissionCode())
//                            .permissions(list.get(position).getPermissions()).request();



                rxPermissions.request(list.get(position).getPermissions()).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            //申请的权限全部允许
                            showToast("获取权限成功");

                        }else{
                            //只要有一个权限被拒绝，就会执行
                            PermissionUtils.showSetPermissionDialog(dialog, PermissionActivity.this, "联系人权限申请", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    goToAppSetting(list.get(position).getPermissionCode());
                                    if(dialog != null){
                                        dialog.dismiss();
                                    }
                                }
                            });

                        }
                    }
                });

            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

}
