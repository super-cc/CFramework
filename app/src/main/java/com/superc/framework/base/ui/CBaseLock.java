package com.superc.framework.base.ui;

import android.content.Context;
import android.os.Bundle;

import com.superc.framework.utils.ToastUtil;

/**
 * 创建日期：2017/10/31 on 11:29
 * 描述：基础Lock
 * 作者：郭士超
 * QQ：1169380200
 */

public abstract class CBaseLock<B> {

    protected Context mContext;
    protected B mBinding;
    protected Bundle mBundle;

    public CBaseLock(Context context, B binding) {
        this.mContext = context;
        this.mBinding = binding;
        init();
    }

    public CBaseLock(Context context, B binding, Bundle bundle) {
        this.mContext = context;
        this.mBinding = binding;
        this.mBundle = bundle;
        init();
    }

    protected abstract void init();

    protected void showToast(String msg) {
        ToastUtil.showShort(mContext, msg);
    }

    protected void showToast(int msg) {
        ToastUtil.showShort(mContext, msg);
    }

}
