package com.superc.framework.lock;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.superc.framework.BR;
import com.superc.framework.R;
import com.superc.cframework.base.ui.CBaseListViewAdapter;
import com.superc.cframework.base.ui.CBaseLock;
import com.superc.cframework.base.ui.CBaseRecyclerViewAdapter;
import com.superc.framework.constant.Connection;
import com.superc.framework.constant.UrlConfig;
import com.superc.framework.databinding.ActivityDemoBinding;
import com.superc.framework.databinding.ItemDemoBinding;
import com.superc.framework.model.Demo;
import com.superc.framework.model.DemoItem;
import com.superc.cframework.network.HttpResponse;
import com.superc.framework.response.DemoResponse;
import com.superc.framework.ui.home.DemoAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 创建日期：2017/10/31 on 13:54
 * 描述：DemoLock，处理一些网络数据，和一些逻辑
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoLock extends CBaseLock<ActivityDemoBinding> {

    private Demo mDemo;
    private List<DemoItem> mListDemo;
    private DemoAdapter mDemoAdapter;
    private CBaseRecyclerViewAdapter mAdapter;

    public DemoLock(Context context, ActivityDemoBinding binding) {
        super(context, binding);
    }

    @Override
    protected void init() {
        // 初始化数据
        mDemo = new Demo("郭士超", "22");

        mListDemo = new ArrayList<DemoItem>();
        mListDemo.add(new DemoItem(mDemo.getName(), mDemo.getAge()));
        mDemoAdapter = new DemoAdapter(mContext, mListDemo, R.layout.item_demo, BR.demoItem);
        mBinding.lvDemo.setAdapter(mDemoAdapter); // 这里或者在xml里设置adapter
        mDemoAdapter.setOnItemClickListener(new CBaseListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding binding, int position) {
                showToast(((ItemDemoBinding) binding).getDemoItem().getName()
                        + mListDemo.get(position).getAge());
            }
        });

        LinearLayoutManager ms = new LinearLayoutManager(mContext);
        mBinding.rvDemo.setLayoutManager(ms);
        mAdapter = new CBaseRecyclerViewAdapter(mContext, mListDemo, R.layout.item_demo, BR.demoItem);
//        mBinding.rvDemo.setAdapter(mAdapter); // 这里或者在xml里设置adapter
        mAdapter.setOnItemClickListener(new CBaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding binding, int position) {
                showToast(((ItemDemoBinding) binding).getDemoItem().getName()
                        + mListDemo.get(position).getAge());
            }
        });

        // 访问网络的方法
        HashMap hashMap = new HashMap();
        hashMap.put("userId", "1");
        Connection.getInstance().post(DemoResponse.class, UrlConfig.DEMO, hashMap, new Connection.ResponseListener() {
            @Override
            public void tryReturn(int id, Object response) {
                switch (id) {
                    case 200:
                        DemoResponse data = (DemoResponse) response;
                        new Demo(data.getData().getName(), String.valueOf(data.getData().getAge()));
                        break;
                    case 202:
                        showToast("用户不存在");
                        break;
                    default:
                        showToast(((HttpResponse)response).getMsg());
                        break;
                }
            }
        });
    }

    public void update(View view) {
        mDemo.notifyChange(); // 刷新数据
        mListDemo.add(new DemoItem(mDemo.getName(), mDemo.getAge()));
        mDemoAdapter.notifyDataSetChanged(); // 刷新数据
        mAdapter.notifyDataSetChanged(); // 刷新数据
    }

    public Demo getDemo() {
        return mDemo; // 让xml中可以调用到Demo
    }

    public DemoAdapter getDemoAdapter() {
        return mDemoAdapter; // 让xml中可以调用到mAdapter
    }

    public CBaseRecyclerViewAdapter getAdapter() {
        return mAdapter;
    }

}
