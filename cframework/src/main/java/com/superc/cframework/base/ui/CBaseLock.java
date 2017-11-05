package com.superc.cframework.base.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.superc.cframework.utils.ToastUtil;

/**
 * 创建日期：2017/10/31 on 11:29
 * 描述：基础Lock
 * 作者：郭士超
 * QQ：1169380200
 */

public abstract class CBaseLock<B> {

    // 上下文
    protected Context mContext;
    // 绑定
    protected B mBinding;
    // Bundle数据
    protected Bundle mBundle;

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param binding 绑定
     */
    public CBaseLock(Context context, B binding) {
        this(context, binding, null);
    }

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param binding 绑定
     * @param bundle 数据
     */
    public CBaseLock(Context context, B binding, Bundle bundle) {
        this.mContext = context;
        this.mBinding = binding;
        this.mBundle = bundle;
        init();
    }

    // 初始化
    protected abstract void init();

    // Toast出Msg
    protected void showToast(String msg) {
        ToastUtil.showShort(mContext, msg);
    }

    // Toast出资源
    protected void showToast(int msg) {
        ToastUtil.showShort(mContext, msg);
    }

    /**
     * 启动Activity
     *
     * @param clazz 启动的Activity的类
     */
    protected void startActivity(Class clazz) {
        startActivity(clazz, null, null);
    }

    /**
     * 启动Activity
     *
     * @param clazz 启动的Activity的类
     * @param datas 传入的Bundle数据
     */
    protected void startActivity(Class clazz, Bundle datas) {
        startActivity(clazz, datas, null);
    }

    /**
     * 启动Activity
     *
     * @param clazz 启动的Activity的类
     * @param datas 传入的Bundle数据
     * @param options Android过渡动画
     */
    protected void startActivity(Class clazz, Bundle datas, Bundle options) {
        Intent intent = new Intent();
        intent.setClass(mContext, clazz);
        if (datas != null) {
            intent.putExtras(datas);
        }
        mContext.startActivity(intent, options);
    }


}
