package com.superc.cframework.base.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 创建日期：2017/10/30 on 13:30
 * 描述：只有一行文字的ListView的Adapter
 * 作者：郭士超
 * QQ：1169380200
 */

public class CBaseSingleTextAdapter extends BaseAdapter {

    private Context mContext; // 上下文
    private ArrayList mTextList; // Text数据
    private float textSize = 14f; // 字号
    private int textColor = Color.BLACK; // 字颜色
    private int textGravity = Gravity.NO_GRAVITY; // 字位置
    private int patingTop, patingBottom, patingLeft, patingRight; // padding
    private int height; // Text高度
    private OnItemClickLisenter mLisenter; // 点击监听
    private boolean isFrist; // 暂时未用

    /**
     * 构造函数
     *
     * @param context 上下文
     * @param textList Text数据
     */
    public CBaseSingleTextAdapter(Context context, ArrayList textList) {
        this.mContext = context;
        this.mTextList = textList;
    }

    @Override
    public int getCount() {
        if (mTextList == null) {
            return 0;
        }
        return mTextList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(mContext);
        } else {
            tv = (TextView) convertView;
        }

        attribute(tv, position);

        return tv;
    }

    // 设置一切
    private void attribute(final TextView tv, final int position) {
        tv.setText((String) mTextList.get(position));
        tv.setTextSize(textSize);
        if (position!=0){
            tv.setTextColor(textColor);
        }
        tv.setGravity(textGravity);
        tv.setPadding(patingLeft, patingTop, patingRight, patingBottom);
        if (height != 0) {
            tv.setHeight(height);
        }

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLisenter != null) {
                    mLisenter.onItemClick(tv, position);
                }
            }
        });

    }

    // 设置Text字号大小
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    // 设置Text字的颜色
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    // 设置Text的显示位置
    public void setTextGravity(int textGravity) {
        this.textGravity = textGravity;
    }

    // 设置Text的Padding
    public void setPating(int patingTop, int patingBottom, int patingLeft, int patingRight) {
        this.patingTop = patingTop;
        this.patingBottom = patingBottom;
        this.patingLeft = patingLeft;
        this.patingRight = patingRight;
    }

    // 设置Text高度
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 设置监听Item点击事件
     *
     * @param lisenter 监听
     */
    public void setOnItemClickLisenter(OnItemClickLisenter lisenter) {
        this.mLisenter = lisenter;
    }

    /**
     * Item点击事件接口
     */
    public interface OnItemClickLisenter {
        void onItemClick(TextView tv, int position);
    }

}
