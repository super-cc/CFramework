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

    // 用来保存运行时临时数据
    private static HashMap<Integer, Object> sHashMap = new HashMap<>();

    public static Object getRunTime(Integer key) {
        return sHashMap.get(key);
    }

    public static void setData(Integer key, Object valus) {
        sHashMap.put(key, valus);
    }
}
