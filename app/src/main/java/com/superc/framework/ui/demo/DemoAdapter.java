package com.superc.framework.ui.demo;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.superc.cframework.base.ui.CBaseListViewAdapter;
import com.superc.framework.databinding.ItemDemoBinding;

import java.util.List;

/**
 * 创建日期：2017/10/31 on 15:44
 * 描述：继承BaseListViewAdapter，只需要复写subTask就可以
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoAdapter extends CBaseListViewAdapter {

    public DemoAdapter(Context context, List list, int layoutId, int variableId) {
        super(context, list, layoutId, variableId);
    }

    @Override
    protected void subTask(ViewDataBinding binding, int position) {
        super.subTask(binding, position);
        // 这里可以写自己一些自己的逻辑，如果不需要，一般直接用BaseListViewAdapter就可以，不需要再构建
        // 写法大概如下
        ((ItemDemoBinding)binding).tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
