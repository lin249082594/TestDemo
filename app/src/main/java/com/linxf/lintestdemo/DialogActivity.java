package com.linxf.lintestdemo;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.linxf.base.BaseActivity;
import com.linxf.base.widget.dialog.BottomViewDialog;
import com.linxf.base.widget.dialog.CustomDialog;
import com.linxf.base.widget.dialog.CustomPopWindows;
import com.linxf.base.widget.dialog.LoadingNoBgDialog;
import com.linxf.base.widget.dialog.LoadingShadeDialog;
import com.linxf.base.widget.dialog.TopDialog;
import com.linxf.base.widget.recycleview.OnItemClickListener;
import com.linxf.lintestdemo.adapter.MainItemAdapter;
import com.linxf.lintestdemo.databinding.FragmentPermissionBinding;
import com.linxf.lintestdemo.entity.MainUi;

import java.util.ArrayList;
import java.util.List;

/**
 * dialog界面
 * Created by Linxf on 2019/4/12.
 */

public class DialogActivity extends BaseActivity {
    private List<MainUi> list = new ArrayList<>();
    private MainItemAdapter mainItemAdapter;
    private FragmentPermissionBinding binding;
    private LoadingNoBgDialog loadingNoBgDialog;
    private LoadingShadeDialog loadingShadeDialog;
    private BottomViewDialog bottomViewDialog;
    private AlertDialog alertDialog;
    private CustomDialog customDialog;
    private TopDialog topDialog;
    private CustomPopWindows customPopWindows;
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.fragment_permission);
        return 0;
    }

    @Override
    public void initView() {
        MainUi item = new MainUi();
        item.setName("阴影背景 progressbar");
        list.add(item);
        item = new MainUi();
        item.setName("无阴影 progressbar");
        list.add(item);
        item = new MainUi();
        item.setName("alertdialog");
        list.add(item);
        item = new MainUi();
        item.setName("自定义dialog");
        list.add(item);
        item = new MainUi();
        item.setName("底部dialog");
        list.add(item);
        item = new MainUi();
        item.setName("顶部dialog");
        list.add(item);
        item = new MainUi();
        item.setName("popwindows");
        list.add(item);

        mainItemAdapter = new MainItemAdapter(this,list);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(mainItemAdapter);
        binding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        mainItemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                switch (position){
                    case 0:
                        loadingShadeDialog.show();

                        break;
                    case 1:
                        loadingNoBgDialog.show();
                        break;
                    case 2:
                        alertDialog.show();
                        break;
                    case 3:
                        customDialog.show();
                        break;
                    case 4:
                        bottomViewDialog.show();
                        break;
                    case 5:
                        topDialog.show();
                        break;
                    case 6:
                        customPopWindows.show(binding.recycleView);
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }

    @Override
    public void initData() {
        initDialog();
    }

    private void initDialog(){
        loadingNoBgDialog = new LoadingNoBgDialog(this);
        loadingShadeDialog = new LoadingShadeDialog(this);
        bottomViewDialog = new BottomViewDialog(this);
        topDialog = new TopDialog(this);
        customPopWindows = new CustomPopWindows(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("这是标题");
        //设置正文
        builder.setMessage("dialog显示内容");

        //添加确定按钮点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        //添加取消按钮点击事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        builder.setCancelable(false);
        //使用构建器创建出对话框对象
        alertDialog = builder.create();


        customDialog = new CustomDialog(this, "这是标题", "这是展示内容", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("确定了");
            }
        });
    }

}
