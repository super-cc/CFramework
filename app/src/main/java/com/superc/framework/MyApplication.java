package com.superc.framework;

import android.app.Application;

/**
 * 创建日期：2017/10/30 on 15:23
 * 描述：项目Application，单例设计模式
 * 作者：郭士超
 * QQ：1169380200
 */

public class MyApplication extends Application{

    private volatile static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        if(instance == null) {
            synchronized(MyApplication.class) {
                if(instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }

}
