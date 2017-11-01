package com.superc.framework.model;

import android.databinding.BaseObservable;

/**
 * 创建日期：2017/10/31 on 14:46
 * 描述：数据模型Demo，如果需要数据刷新就继承BaseObservable
 * 作者：郭士超
 * QQ：1169380200
 */

public class Demo extends BaseObservable{

    private String name;
    private String age;

    public Demo(String name, String age) {
        this.name = name;
        this.age = age;
    }

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
}
