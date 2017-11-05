package com.superc.cframework.base.ui;

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

    protected Context mContext; // 上下文
    protected List<T> mDataList; // 数据列表
    private int layoutId; // 单布局
    private int variableId; // DataBinding的BR
    private OnItemClickListener mListener; // ItemClick监听

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param dataList 数据列表
     * @param layoutId 单布局
     * @param variableId DataBinding的BR
     */
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

    /**
     * 其它操作
     *
     * @param binding 绑定
     * @param position 列表位置
     */
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

    /**
     * 设置监听Item点击事件
     *
     * @param onItemClickListener 监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    /**
     * Item点击事件接口
     */
    public interface OnItemClickListener {
        void onItemClick(ViewDataBinding binding, int position);
    }

}
