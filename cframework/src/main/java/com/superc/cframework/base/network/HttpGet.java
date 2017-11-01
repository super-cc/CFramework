package com.superc.cframework.base.network;

import com.superc.cframework.utils.LogUtil;

import okhttp3.Request;

/**
 * GET 请求
 * Created by Super丶C on 2017/10/30.
 */

public abstract class HttpGet<T> extends HttpTask<T> {

    @Override
    protected Request createRequest() {
        String url = createUrl();
        LogUtil.i("GET", "url:" + url);
        return new Request.Builder().url(url).build();
    }
}
