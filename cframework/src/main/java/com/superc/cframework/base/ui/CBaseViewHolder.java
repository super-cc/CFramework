package com.superc.cframework.base.ui;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建日期：2017/10/31 on 17:34
 * 描述：CBaseViewHolder，BaseRecyclerViewAdapter使用
 * 作者：郭士超
 * QQ：1169380200
 */

public class CBaseViewHolder extends RecyclerView.ViewHolder {

    protected Context mContext; // 上下文

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param itemView 布局View
     * @param binding 绑定
     */
    public CBaseViewHolder(Context context, View itemView, final ViewDataBinding binding) {
        super(itemView);

    }
}
