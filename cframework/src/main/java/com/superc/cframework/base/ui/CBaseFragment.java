package com.superc.cframework.base.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superc.cframework.utils.ToastUtil;

/**
 * 创建日期：2017/10/30 on 13:56
 * 描述：基础Fragmentation
 * 作者：郭士超
 * QQ：1169380200
 */

public abstract class CBaseFragment extends Fragment {

    // 所在Activity
    protected Activity mActivity;
    // 传到所在Activity的Bundle数据
    protected Bundle mBundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mActivity = getActivity();
        mBundle = mActivity.getIntent().getExtras();

        View view = initBinding(inflater, container);

        init();

        return view;
    }

    // 初始化DataBinding，绑定View
    protected abstract View initBinding(LayoutInflater inflater, ViewGroup container);

    // 初始化操作
    protected abstract void init();

    // Toast出Msg
    protected void showToast(String msg) {
        ToastUtil.showShort(mActivity, msg);
    }

    // Toast出资源
    protected void showToast(int msg) {
        ToastUtil.showShort(mActivity, msg);
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
     * @param clazz   启动的Activity的类
     * @param datas   传入的Bundle数据
     * @param options Android过渡动画
     */
    protected void startActivity(Class clazz, Bundle datas, Bundle options) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clazz);
        if (datas != null) {
            intent.putExtras(datas);
        }
        startActivity(intent, options);
    }


}
