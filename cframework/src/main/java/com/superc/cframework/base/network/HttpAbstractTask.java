package com.superc.cframework.base.network;


import android.os.AsyncTask;

import com.superc.cframework.utils.LogUtil;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络任务
 * Created by Super丶C on 2017/10/30.
 */

public abstract class HttpAbstractTask<T> extends AsyncTask<Void, Void, Object> {

    private static final OkHttpClient CLIENT;
    private OnResponseCallback callback;

    static {
        CLIENT = new OkHttpClient();
    }

    private WeakReference<OnResponseCallback> mCallback;

    public void setOnResponseCallback(OnResponseCallback callback) {
        mCallback = new WeakReference<>(callback);
        this.callback=callback;
    }

    public boolean hasCallback() {
        return mCallback != null;
    }

    /**
     * 创建请求体
     * <p>
     * <b>GET SAMPLE</b>
     * <div>
     * Request request = new Request.Builder()
     * .url(url)
     * .build();
     * <div/>
     * </p>
     * <p>
     * <b>POST SAMPLE</b>
     * <div>
     * RequestBody body = new FormBody.Builder().add("a","1").addEncoded("b","b").build();
     * Request request = new Request.Builder()
     * .url(url)
     * .post(body)
     * .build();
     * <div/>
     * </p>
     *
     * @return 请求体
     */
    protected abstract Request createRequest();

    /**
     * 将返回的数据解析为实体
     *
     * @param responseBody Http响应体，Json格式
     * @return 响应的实例
     */
    protected abstract Object parse(String responseBody);

    /**
     * 任务标识
     *
     * @return 应用程序惟一的任务标识码
     */
    protected abstract int identifier();

    public String _run() {
        Request request = createRequest();
        if (request == null) {
            throw new IllegalStateException("createRequest() should not return null.");
        }
        try {
            Response response = CLIENT.newCall(request).execute();
            String body = response.body().string().trim();
            if (String.valueOf(response.code()).startsWith("2") && body.length() != 0 && body
                    .startsWith("{")) {
                return body;
            } else {
                return wrap(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DefaultConfig.HTTP_FALLBACK_RESPONSE;
    }

    public Object run() {
        String responseBody = _run();
        LogUtil.i("HttpTask", "response:" + responseBody);
        return parse(responseBody);
    }

    private String wrap(Response response) {
        return String.format(Locale.CHINA, "{\"code\": -2,\"msg\":\"%s\",\"status\":%d}",
                response.message(), response.code());
    }

    @Override
    protected final Object doInBackground(Void... voids) {
        String responseBody = _run();

        return parse(responseBody);
    }

    @Override
    protected final void onPostExecute(Object response) {
        if (mCallback != null) {
            OnResponseCallback callback = mCallback.get();
            if (callback != null) {
                callback.onResponse(identifier(), response);
            }else {
                this.callback.onResponse(identifier(), response);
            }
        }
    }

    /**
     * 立即执行
     */
    public void executeNow() {
        executeOnExecutor(THREAD_POOL_EXECUTOR);
    }

    public interface OnResponseCallback {
        void onResponse(int identifier, Object response);
    }
}
