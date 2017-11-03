package com.superc.cframework.base.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建日期：2017/10/30 on 13:56
 * 描述：基础Fragmentation
 * 作者：郭士超
 * QQ：1169380200
 */

public abstract class CBaseFragment extends Fragment{

    protected Activity mActivity;
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

    protected abstract View initBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void init();

    protected void startActivity(Class clazz) {
        startActivity(clazz, null);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}
