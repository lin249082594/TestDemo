package com.linxf.lintestdemo;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.linxf.base.BaseActivity;
import com.linxf.base.widget.datepicker.CustomDatePicker;
import com.linxf.base.widget.datepicker.DateFormatUtils;
import com.linxf.lintestdemo.databinding.ActivityDataPickerBinding;

/**
 * 类说明
 * Created by Linxf on 2019/4/26.
 */

public class DatePickActivity extends BaseActivity{
    private CustomDatePicker mDatePicker;
    private CustomDatePicker mTimePicker;
    private CustomDatePicker mMonthPicker;
    private ActivityDataPickerBinding binding;
    @Override
    public int getLayoutId() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_picker);
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initDatePicker();
        initTimePicker();
        initMonthPicker();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(v.getId() == R.id.time1TxV){
            mDatePicker.show(binding.time1ContentTxV.getText().toString());
        } else if(v.getId() == R.id.time2TxV){

            mTimePicker.show(binding.time2ContentTxV.getText().toString());
        } else if(v.getId() == R.id.time3TxV){
            mMonthPicker.show(binding.time3ContentTxV.getText().toString());
        }
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", DateFormatUtils.DATE_FORMAT_PATTERN_YMD);
        long endTimestamp = System.currentTimeMillis();

        binding.time1ContentTxV.setText(DateFormatUtils.long2Str(endTimestamp, DateFormatUtils.DATE_FORMAT_PATTERN_YMD));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                binding.time1ContentTxV.setText(DateFormatUtils.long2Str(timestamp, DateFormatUtils.DATE_FORMAT_PATTERN_YMD));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(DateFormatUtils.DATE_FORMAT_PATTERN_YMD);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    private void initTimePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01 18:00", DateFormatUtils.DATE_FORMAT_PATTERN_YMD_HM);
        long endTimestamp = System.currentTimeMillis();

        binding.time2ContentTxV.setText(DateFormatUtils.long2Str(endTimestamp, DateFormatUtils.DATE_FORMAT_PATTERN_YMD_HM));

        // 通过时间戳初始化日期，毫秒级别
        mTimePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                binding.time2ContentTxV.setText(DateFormatUtils.long2Str(timestamp, DateFormatUtils.DATE_FORMAT_PATTERN_YMD_HM));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mTimePicker.setCancelable(false);
        // 不显示时和分
        mTimePicker.setCanShowPreciseTime(DateFormatUtils.DATE_FORMAT_PATTERN_YMD_HM);
        // 不允许循环滚动
        mTimePicker.setScrollLoop(false);
        // 不允许滚动动画
        mTimePicker.setCanShowAnim(false);
    }

    private void initMonthPicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", DateFormatUtils.DATE_FORMAT_PATTERN_YM);
        long endTimestamp = System.currentTimeMillis();

        binding.time3ContentTxV.setText(DateFormatUtils.long2Str(endTimestamp, DateFormatUtils.DATE_FORMAT_PATTERN_YM));

        // 通过时间戳初始化日期，毫秒级别
        mMonthPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                binding.time3ContentTxV.setText(DateFormatUtils.long2Str(timestamp, DateFormatUtils.DATE_FORMAT_PATTERN_YM));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        mMonthPicker.setCancelable(false);
        // 不显示时和分
        mMonthPicker.setCanShowPreciseTime(DateFormatUtils.DATE_FORMAT_PATTERN_YM);
        // 不允许循环滚动
        mMonthPicker.setScrollLoop(false);
        // 不允许滚动动画
        mMonthPicker.setCanShowAnim(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatePicker.onDestroy();
        mTimePicker.onDestroy();
        mMonthPicker.onDestroy();
    }
}
