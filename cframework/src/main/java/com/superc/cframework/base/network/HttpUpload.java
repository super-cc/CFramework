package com.superc.cframework.base.network;

import com.superc.cframework.utils.LogUtil;

import java.io.File;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 上传
 * Created by Super丶C on 2017/10/30.
 */

public abstract class HttpUpload<T> extends HttpTask<T> {

    /**
     * 文件参数
     *
     * @return 一个包含文件及其参数名称的Map。key为参数名，value为文件对象
     */
    public abstract Map<String, File> getFiles();

    /**
     * 其他作为表单数据的参数
     */
    public abstract Map<String, String> getExtraParams();

    @Override
    protected Request createRequest() {
        String url = createUrl();
        Map<String, File> files = getFiles();
        Map<String, String> params = getExtraParams();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        StringBuilder sb = new StringBuilder();
        sb.append(" form:\n");

        if (files != null && files.size() > 0) {
            for (Map.Entry<String, File> entry : files.entrySet()) {
                File file = entry.getValue();
                if (file != null) {
                    builder.addFormDataPart(entry.getKey(), file.getName(), RequestBody.create(null, file));
                    sb.append(entry.getKey()).append('=').append(file.getName()).append('\n');
                }
            }
        }
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
                sb.append(entry.getKey()).append('=').append(entry.getValue()).append('\n');
            }
        }
        LogUtil.i("UPLOAD", "url:" + url + sb.toString());
        return new Request.Builder().url(createUrl()).post(builder.build()).build();
    }
}
