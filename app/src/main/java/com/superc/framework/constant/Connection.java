package com.superc.framework.constant;

import android.content.Context;

import com.superc.framework.MyApplication;
import com.superc.framework.base.network.HttpAbstractTask;
import com.superc.framework.network.HttpGetTask;
import com.superc.framework.network.HttpPostTask;
import com.superc.framework.network.HttpResponse;
import com.superc.framework.network.HttpTaskSubmit;
import com.superc.framework.response.UserResponse;
import com.superc.framework.utils.SPUtil;
import com.superc.framework.utils.ToastUtil;

import java.util.HashMap;

/**
 * 接口地址分类
 * Created by Super丶C on 2017/10/30.
 */

public class Connection {

    // 这个用来访问网络，全局用一个，节省内存，不需要再new新的
    private static Connection instance;

    private Context mContext;

    private static final String USER_ID = "user_id";
    private static final String TOKEN = "token";
    private static final String SUCCESS = "200"; // 成功返回的code码
    private static final String TOKEN_ERROR = "999"; // Token错误返回码

    private Connection(){

    }

    public static Connection getInstance() {
        if(instance == null) {
            synchronized(Connection.class) {
                if(instance == null) {
                    instance = new Connection();
                }
            }
        }
        return instance;
    }

    /**
     * Toast错误的Get请求
     *
     * @param clazz    response类
     * @param url      访问的url
     * @param data     参数
     * @param listener 响应回调
     */
    public void get(Class clazz, String url, HashMap data, final ResponseListener listener) {
        if (url == null || data == null) return;
        HttpGetTask httpGetTask = new HttpGetTask(url, data, url.hashCode(), clazz);
        HttpTaskSubmit.executeTask(httpGetTask, new HttpAbstractTask.OnResponseCallback() {
            @Override
            public void onResponse(int identifier, Object response) {
                listener.tryReturn(identifier, response);
            }
        });
    }

    /**
     * 不Toast错误的Post请求
     *
     * @param clazz    response类
     * @param url      访问的url
     * @param data     参数
     * @param listener 响应回调
     */
    public void post(Class clazz, String url, HashMap data, final ResponseListener listener) {
        if (url == null || data == null) return;
        HttpPostTask httpPostTask = new HttpPostTask(url, data, url.hashCode(), clazz);
        HttpTaskSubmit.executeTask(httpPostTask, new HttpAbstractTask.OnResponseCallback() {
            @Override
            public void onResponse(int identifier, Object response) {
                if (((HttpResponse) response).getCode().equals(SUCCESS)) {
                    listener.tryReturn(Integer.parseInt(SUCCESS), response);
                } else {
                    listener.tryReturn(Integer.parseInt(((HttpResponse) response).getCode()), response);
                }
            }
        });
    }

    /**
     * Toast错误的Post请求，错误不回调
     *
     * @param clazz    response类
     * @param url      访问的url
     * @param data     参数
     * @param listener 响应回调
     */
    public void postS(Class clazz, String url, HashMap data, final ResponseListener listener) {
        if (url == null || data == null) return;
        HttpPostTask httpPostTask = new HttpPostTask(url, data, url.hashCode(), clazz);
        HttpTaskSubmit.executeTask(httpPostTask, new HttpAbstractTask.OnResponseCallback() {
            @Override
            public void onResponse(int identifier, Object response) {
                if (((HttpResponse) response).getCode().equals(SUCCESS)) {
                    listener.tryReturn(Integer.parseInt(SUCCESS), response);
                } else {
                    if (((HttpResponse) response).getMsg() != null && !((HttpResponse) response).getMsg().equals("")) {
                        ToastUtil.showShort(MyApplication.getInstance(), ((HttpResponse) response).getMsg());
                    }
                }
            }
        });
    }

    /**
     * Toast错误的Post请求，错误不回调
     * 登入保存Token
     *
     * @param clazz    response类
     * @param url      访问的url
     * @param data     参数
     * @param listener 响应回调
     */
    public void postLogin(Class clazz, String url, HashMap data, final ResponseListener listener) {
        if (url == null || data == null) return;
        HttpPostTask httpPostTask = new HttpPostTask(url, data, url.hashCode(), clazz);
        HttpTaskSubmit.executeTask(httpPostTask, new HttpAbstractTask.OnResponseCallback() {
            @Override
            public void onResponse(int identifier, Object response) {
                if (((HttpResponse) response).getCode().equals(SUCCESS)) {
                    listener.tryReturn(Integer.parseInt(SUCCESS), response);
                    UserResponse data = (UserResponse) response;
                    SPUtil.put(MyApplication.getInstance(), USER_ID, data.getData().getUserId());
                    SPUtil.put(MyApplication.getInstance(), TOKEN, data.getData().getToken());
                } else {
                    if (((HttpResponse) response).getMsg() != null && !((HttpResponse) response).getMsg().equals("")) {
                        ToastUtil.showShort(MyApplication.getInstance(), ((HttpResponse) response).getMsg());
                    }
                }
            }
        });
    }

    /**
     * 不Toast错误的Post请求带Token
     *
     * @param clazz    response类
     * @param url      访问的url
     * @param data     参数
     * @param listener 响应回调
     */
    public void postToken(Class clazz, String url, HashMap data, final ResponseListener listener) {
        if (url == null || data == null) return;
        data.put(USER_ID, SPUtil.get(MyApplication.getInstance(), USER_ID, ""));
        data.put(TOKEN, SPUtil.get(MyApplication.getInstance(), TOKEN, ""));
        HttpPostTask httpPostTask = new HttpPostTask(url, data, url.hashCode(), clazz);
        HttpTaskSubmit.executeTask(httpPostTask, new HttpAbstractTask.OnResponseCallback() {
            @Override
            public void onResponse(int identifier, Object response) {
                if (((HttpResponse) response).getCode().equals(SUCCESS)) {
                    listener.tryReturn(Integer.parseInt(SUCCESS), response);
                } else if (((HttpResponse) response).getCode().equals(TOKEN_ERROR)) {
                    listener.tryReturn(Integer.parseInt(TOKEN_ERROR), response);
                    toLogin();
                } else {
                    listener.tryReturn(Integer.parseInt(((HttpResponse) response).getCode()), response);
                }
            }
        });
    }

    /**
     * Toast错误的Post请求带Token
     *
     * @param clazz    response类
     * @param url      访问的url
     * @param data     参数
     * @param listener 响应回调
     */
    public void postTokenS(Class clazz, String url, HashMap data, final ResponseListener listener) {
        if (url == null || data == null) return;
        data.put(USER_ID, SPUtil.get(MyApplication.getInstance(), USER_ID, ""));
        data.put(TOKEN, SPUtil.get(MyApplication.getInstance(), TOKEN, ""));
        HttpPostTask httpPostTask = new HttpPostTask(url, data, url.hashCode(), clazz);
        HttpTaskSubmit.executeTask(httpPostTask, new HttpAbstractTask.OnResponseCallback() {
            @Override
            public void onResponse(int identifier, Object response) {
                if (((HttpResponse) response).getCode().equals(SUCCESS)) {
                    listener.tryReturn(Integer.parseInt(SUCCESS), response);
                } else if (((HttpResponse) response).getCode().equals(TOKEN_ERROR)) {
                    listener.tryReturn(Integer.parseInt(TOKEN_ERROR), response);
                    toLogin();
                } else {
                    if (((HttpResponse) response).getMsg() != null && !((HttpResponse) response).getMsg().equals("")) {
                        ToastUtil.showShort(MyApplication.getInstance(), ((HttpResponse) response).getMsg());
                    }
                }

            }
        });
    }

    // 如果token不一致，跳到Login页面和一些其它逻辑可以写在这
    private void toLogin() {

    }


    public interface ResponseListener {
        void tryReturn(int id, Object response);
    }

    public interface noListener {
        void tryReturn(boolean isComm);
    }
}
