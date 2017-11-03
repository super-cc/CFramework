package com.superc.framework.ui.home;

import android.databinding.DataBindingUtil;

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

    @Override
    protected void initBinding() {

        // 数据绑定操作，可以套用代码
        ActivityDemoBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_demo);
        DemoLock demoLock = new DemoLock(this, mBinding);
        mBinding.setDemoLock(demoLock);

    }

    @Override
    protected void init() {

    }

}
