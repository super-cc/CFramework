package com.superc.framework.ui.demo;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.superc.framework.R;
import com.superc.cframework.base.ui.CBaseActivity;
import com.superc.framework.databinding.ActivityDemoBinding;
import com.superc.framework.lock.DemoLock;

/**
 * 创建日期：2017/10/30 on 13:55
 * 描述：一个Demo
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoActivity extends CBaseActivity {

    private ActivityDemoBinding mBinding;
    private DemoLock mLock;

    @Override
    protected void initBinding() {

        // 数据绑定操作，可以套用代码
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        mLock = new DemoLock(this, mBinding);
        mBinding.setDemoLock(mLock);

    }

    @Override
    protected void init() {

    }


}
