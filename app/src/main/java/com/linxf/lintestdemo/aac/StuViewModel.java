package com.linxf.lintestdemo.aac;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.linxf.base.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class StuViewModel extends ViewModel {
    private MutableLiveData<List<StUser>> mDatas = new MutableLiveData<>();
    public StuViewModel(){
        mDatas.setValue(new ArrayList<>());
    }


    private LiveData<List<String>> mMapData = Transformations.map(mDatas,value -> {
        return Observable.fromIterable(value).flatMap(new Function<StUser, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull StUser stUsers) throws Exception {
                return Observable.just("学号:" + stUsers.getStuNo() + " 姓名:" + stUsers.getName() + " 年龄:" + stUsers.getAge());
            }
        }).toList().blockingGet();
    });

    public LiveData<List<String>> mSwitcMapData = Transformations.switchMap(mDatas,value ->{
        MutableLiveData<List<String>> item = new MutableLiveData<List<String>>();
        item.setValue(Observable.fromIterable(value).flatMap(new Function<StUser, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull StUser stUser) throws Exception {
                return Observable.just("学生" + stUser.getName() + " 成绩:" + stUser.getGrade());
            }
        }).toList().blockingGet());
        return item;
    });


    public void addData(StUser data) {
        if(mDatas.getValue()  == null){
            mDatas.setValue(new ArrayList<>());
        }
        List<StUser> list = mDatas.getValue();
        list.add(data);
        mDatas.setValue(list);
    }
    public void deleteData(int position) {
        if(mDatas.getValue()  == null && position >= mDatas.getValue().size())
            return;
        List<StUser> list = mDatas.getValue();
        list.remove(position);
        mDatas.setValue(list);
    }
    public MutableLiveData<List<StUser>> getData() {
        return mDatas;
    }

    public LiveData<List<String>> getMapData(){
        return mMapData;
    }

    public LiveData<List<String>> getSwitchMapData(){
        return mSwitcMapData;
    }
}
