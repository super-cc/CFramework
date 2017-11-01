package com.superc.framework.base.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 创建日期：2017/10/30 on 13:54
 * 描述：基础ListView的Adapter，可通用，继承一般只需要复写subTask就可以
 * 作者：郭士超
 * QQ：1169380200
 */

public class CBaseListViewAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDataList;
    private int layoutId;//单布局
    private int variableId;
    private OnItemClickListener mListener;

    public CBaseListViewAdapter(Context context, List<T> dataList, int layoutId, int variableId) {
        this.mContext = context;
        this.mDataList = dataList;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutId, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        if (variableId != 0) {
            binding.setVariable(variableId, mDataList.get(position));
        }
        subTask(binding, position);

        return binding.getRoot();
    }

    protected void subTask(final ViewDataBinding binding, final int position) {
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(binding, position);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(ViewDataBinding binding, int position);
    }
}