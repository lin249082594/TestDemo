package com.linxf.lintestdemo.dagger;

import com.linxf.lintestdemo.DaggerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 类说明
 * Created by Linxf on 2019/5/24.
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(DaggerActivity daggerActivity);
}
