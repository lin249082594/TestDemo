package com.linxf.lintestdemo.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxf.base.BaseFragment;
import com.linxf.base.utils.PermissionUtils;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.R;
import com.linxf.lintestdemo.adapter.PermissionAdapter;
import com.linxf.lintestdemo.databinding.FragmentPermissionBinding;
import com.linxf.lintestdemo.entity.PermissionModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * 类说明
 * Created by Linxf on 2019/4/22.
 */

public class PermissionFragment extends BaseFragment {
    private FragmentPermissionBinding binding;
    private List<PermissionModel> list = new ArrayList<>();
    private PermissionAdapter adapter;
    private AlertDialog dialog;
    private RxPermissions rxPermissions;
    @Override
    public int getLayoutId(LayoutInflater inflater, ViewGroup container) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_permission,container,false);
        mRootView = binding.getRoot();

        return 0;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
        permission.setName("打电话权限");
        permission.setPermissionCode(PermissionUtils.REQUEST_CAMERA);
        permission.setPermissions(PermissionUtils.PERMISSIONS_CAMERA);
        list.add(permission);
        permission = new PermissionModel();
        permission.setName("存储空间权限");
        permission.setPermissionCode(PermissionUtils.REQUEST_SDCARD);
        permission.setPermissions(PermissionUtils.PERMISSIONS_SDCARD);
        list.add(permission);

        adapter = new PermissionAdapter(getContext(),list);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(adapter);
        binding.recycleView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o,final int position) {
                rxPermissions.request(list.get(position).getPermissions()).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            //申请的权限全部允许
                            showToast("获取权限成功");

                        }else{
                            //只要有一个权限被拒绝，就会执行
                            PermissionUtils.showSetPermissionDialog(dialog, getContext(), "联系人权限申请", new DialogInterface.OnClickListener() {
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
//        RxView.clicks(view)
//                .compose(new RxPermissions(this).ensure(Manifest.permission.CAMERA))
//                .subscribe(granted -> Toast.makeText(this, granted ? "已赋予权限" : "已拒绝权限", Toast.LENGTH_SHORT).show());
    }


}
