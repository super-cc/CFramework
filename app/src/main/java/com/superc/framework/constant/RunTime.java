package com.superc.framework.constant;

import android.content.Context;

import com.superc.framework.MyApplication;

import java.util.HashMap;

/**
 * 创建日期：2017/10/30 on 16:58
 * 描述：运行数据临时储存
 * 作者：郭士超
 * QQ：1169380200
 */

public class RunTime {

    // 数据的key
    public static final int ZZZ_ID = 10000;

    // 这个用来访问网络，全局用一个，节省内存，不需要再new新的
    public static Connection sConnection = new Connection();
    // 这个可以调用MyApplication，或使用MyApplication.getInstance()来调用也可以，在这里方便一些
    public static Context sAppContext = MyApplication.getInstance();

    // 用来保存运行时临时数据
    private static HashMap<Integer, Object> sHashMap = new HashMap<>();

    public static Object getRunTime(Integer key) {
        return sHashMap.get(key);
    }

    public static void setData(Integer key, Object valus) {
        sHashMap.put(key, valus);
    }
}
