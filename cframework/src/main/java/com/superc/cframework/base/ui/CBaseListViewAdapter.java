package com.superc.cframework.base.ui;

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

    /**
     * 其它操作
     *
     * @param binding 绑定
     * @param position 列表位置
     */
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