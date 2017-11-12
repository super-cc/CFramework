package com.superc.cframework.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 创建日期：2017/10/30 on 16:34
 * 描述：软键盘相关辅助类
 * 作者：郭士超
 * QQ：1169380200
 */

public class KeyBoardUtil {

    /**
     * 是否有显示键盘
     *
     * @param activity 活动
     * @return 是否有显示键盘
     */
    public static boolean isSoftShowing(Activity activity) {
        //获取当前屏幕内容的高度
        int screenHeight = activity.getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom != 0;
    }

    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext 上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText  输入框
     * @param mContext 上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext)
    {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

}
