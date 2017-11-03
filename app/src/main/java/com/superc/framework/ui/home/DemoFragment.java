package com.superc.framework.ui.home;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.superc.framework.R;
import com.superc.cframework.base.ui.CBaseFragment;
import com.superc.framework.databinding.FragmentDemoBinding;
import com.superc.framework.lock.DemLock;

/**
 * 创建日期：2017/10/31 on 14:07
 * 描述：
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoFragment extends CBaseFragment {

    private FragmentDemoBinding mBinding;
    private DemLock mLock;

    @Override
    protected View initBinding(LayoutInflater inflater, ViewGroup container) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_demo, container, false);
        mLock = new DemLock(mActivity, mBinding);
        mBinding.setDemLock(mLock);

        return mBinding.getRoot();
    }

    @Override
    protected void init() {
        View view = new View(mActivity);
    }



}
