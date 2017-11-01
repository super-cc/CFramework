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
    private Context mContext;
    private ArrayList mTextList;
    private float textSize = 14f;
    private int textColor = Color.BLACK;
    private int textGravity = Gravity.NO_GRAVITY;
    private int patingTop, patingBottom, patingLeft, patingRight;
    private int hei;
    private OnItemClickLisenter mLisenter;
    private boolean isFrist;

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

    private void attribute(final TextView tv, final int position) {
        tv.setText((String) mTextList.get(position));
        tv.setTextSize(textSize);
        if (position!=0){
            tv.setTextColor(textColor);
        }
        tv.setGravity(textGravity);
        tv.setPadding(patingLeft, patingTop, patingRight, patingBottom);
        if (hei != 0) {
            tv.setHeight(hei);
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

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextGravity(int textGravity) {
        this.textGravity = textGravity;
    }

    public void setPating(int patingTop, int patingBottom, int patingLeft, int patingRight) {
        this.patingTop = patingTop;
        this.patingBottom = patingBottom;
        this.patingLeft = patingLeft;
        this.patingRight = patingBottom;
    }

    public void setHei(int hei) {
        this.hei = hei;
    }

    public void setOnItemClickLisenter(OnItemClickLisenter lisenter) {
        this.mLisenter = lisenter;
    }

    public interface OnItemClickLisenter {
        void onItemClick(TextView tv, int position);
    }

}
