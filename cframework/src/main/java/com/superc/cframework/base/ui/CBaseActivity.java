package com.superc.cframework.base.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.superc.cframework.utils.AtyManager;
import com.superc.cframework.utils.ToastUtil;

/**
 * 创建日期：2017/10/30 on 13:55
 * 描述：基础Activity
 * 作者：郭士超
 * QQ：1169380200
 */

public abstract class CBaseActivity extends AppCompatActivity {

    // 传过来的Bundle数据
    protected Bundle mBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSys();

        AtyManager.getInstance().addActivity(this);  // 加入AtyManager管理类

        mBundle = getIntent().getExtras();

        initBinding();

        init();
    }

    // 在setContentView前的初始化
    protected void initSys() {

    }

    // 初始化DataBinding，绑定View
    protected abstract void initBinding();

    // 初始化
    protected abstract void init();

    // 获取上下文
    public Context getContext() {
        return CBaseActivity.this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AtyManager.getInstance().finishActivity(this);  // 从AtyManager管理类中删除
    }

    // Toast出Msg
    protected void showToast(String msg) {
        ToastUtil.showShort(this, msg);
    }

    // Toast出资源
    protected void showToast(int msg) {
        ToastUtil.showShort(this, msg);
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
        intent.setClass(this, clazz);
        if (datas != null) {
            intent.putExtras(datas);
        }
        startActivity(intent, options);
    }


}
