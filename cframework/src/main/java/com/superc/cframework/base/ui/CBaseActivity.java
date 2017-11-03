package com.superc.cframework.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.superc.cframework.utils.AtyManager;

/**
 * 创建日期：2017/10/30 on 13:55
 * 描述：基础Activity
 * 作者：郭士超
 * QQ：1169380200
 */

public abstract class CBaseActivity extends AppCompatActivity {

    protected Bundle mBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AtyManager.getInstance().addActivity(this);  // 加入AtyManager管理类

        mBundle = getIntent().getExtras();

        initBinding();

        init();
    }

    protected abstract void initBinding();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AtyManager.getInstance().finishActivity(this);  // 从AtyManager管理类中删除
    }

    protected void startActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    protected void startActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
