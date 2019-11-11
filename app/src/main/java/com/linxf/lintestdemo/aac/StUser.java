package com.linxf.lintestdemo.aac;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 * Created by Linxf on 2019/5/28.
 */

public class StUser {
    private String name;
    private String age;
    private String stuNo;
    private String grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
