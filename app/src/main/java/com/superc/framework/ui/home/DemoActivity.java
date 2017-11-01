package com.superc.framework.ui.home;

import android.databinding.DataBindingUtil;

import com.superc.framework.R;
import com.superc.framework.base.ui.BaseActivity;
import com.superc.framework.databinding.ActivityDemoBinding;
import com.superc.framework.lock.DemoLock;

/**
 * 创建日期：2017/10/30 on 13:55
 * 描述：一个Demo
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoActivity extends BaseActivity {

    @Override
    protected void initBinding() {

        ActivityDemoBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        DemoLock demoLock = new DemoLock(this, mBinding);
        mBinding.setDemoLock(demoLock);

    }

}
