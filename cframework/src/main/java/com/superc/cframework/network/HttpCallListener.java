package com.superc.cframework.network;

import android.support.annotation.NonNull;

/**
 * 网络回调
 * Created by Super丶C on 2017/10/30.
 */

public interface HttpCallListener<T> {
    void Start(String URL);

    void Success(String URL, @NonNull T bean);

    void Error(String URL);
}
