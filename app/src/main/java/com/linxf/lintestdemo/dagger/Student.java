package com.linxf.lintestdemo.dagger;

import javax.inject.Inject;

/**
 * 类说明
 * Created by Linxf on 2019/5/24.
 */

public class Student {
    private String mess="Student的实例是注解方式注入的";

    @Inject
    public Student(){

    }

    public String showMessage(){
        return mess;
    }
}
