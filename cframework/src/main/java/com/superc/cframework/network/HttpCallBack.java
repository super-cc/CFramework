package com.superc.cframework.network;

import com.superc.cframework.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 网络
 * Created by Super丶C on 2017/10/30.
 */

public abstract class HttpCallBack<T> implements Callback {

    HttpCallListener httpCallListener;
    private Class<T> cls = null;
    private String URL;

    public HttpCallBack(String URL, HttpCallListener<T> httpCallListener, Class<T> cls) {
        this.httpCallListener = httpCallListener;
        this.cls = cls;
        this.URL = URL;

    }
    @Override
    public void onFailure(Call call, IOException e) {
        if (httpCallListener != null) {
            httpCallListener.Error(URL);
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (httpCallListener != null) {
            String returnString = response.body().string();
            LogUtil.i("Http:"+URL, returnString);
            if (JsonUtil.isJson(returnString)) {
              T t= GsonUtil.getGson().fromJson(returnString, cls);
                httpCallListener.Success(URL, t);
            } else {
                httpCallListener.Error(URL);
            }
        }
    }

}
