package com.superc.framework.base.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 创建日期：2017/10/30 on 13:55
 * 描述：基础BRecyclerView的Adapter，继承一般只需要复写subTask就可以
 * 作者：郭士超
 * QQ：1169380200
 */

public class CBaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {

    protected Context mContext;
    protected List<T> mDataList;
    private int layoutId;//单布局
    private int variableId;
    private OnItemClickListener mListener;

    public CBaseRecyclerViewAdapter(Context context, List<T> dataList, int layoutId, int variableId) {
        this.mContext = context;
        this.mDataList = dataList;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                layoutId,
                parent,
                false);
        return new CBaseViewHolder(mContext, mBinding.getRoot(), mBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewDataBinding binding = DataBindingUtil.findBinding(holder.itemView);
        binding.setVariable(variableId, mDataList.get(position));
        subTask(binding, position);
    }

    private void subTask(final ViewDataBinding binding, final int position) {
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(binding, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewDataBinding binding, int position);
    }

}
