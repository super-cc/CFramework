package com.superc.cframework.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.superc.cframework.utils.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 下载文件
 * Created by Super丶C on 2017/10/30.
 */

public class HttpFileTask {

    private final OkHttpClient client = new OkHttpClient();

    public void UpLoadPicture(String url, final HashMap<String, String> data, final ResponseListener listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("user_id", data.get("user_id"));
        hashMap.put("mobile", data.get("mobile"));
        hashMap.put("nickname", data.get("nickname"));
        hashMap.put("sex", data.get("sex"));
        String image_name = data.get("file");
        if (image_name != null) {
            File file = new File(image_name);
            hashMap.put("file", file);

        }
        formRequset(url, hashMap, HttpResponse.class, new HttpCallListener<HttpResponse>() {
            @Override
            public void Start(String URL) {
                Log.i("file", URL);
            }

            @Override
            public void Success(String URL, @NonNull HttpResponse bean) {
                listener.tryReturn(Integer.parseInt(bean.getCode()), bean);
            }

            @Override
            public void Error(String URL) {
                Log.i("file", URL);
            }
        });
    }

    public <T> void formRequset(String URL, HashMap<String, Object> param, Class<T> type, HttpCallListener<T> httpCallListener) {
        if (httpCallListener != null) {
            httpCallListener.Start(URL);
        }
        Request request = new Request.Builder().url(URL).post(toFormParam(param)).build();
        Call call = client.newCall(request);
        call.enqueue(new HttpCallBack<T>(URL, httpCallListener, type) {
        });
    }

    private RequestBody toFormParam(HashMap<String, Object> param) {
        Iterator iterator = param.entrySet().iterator();
        MultipartBody.Builder formBody = new MultipartBody.Builder();
        formBody.setType(MultipartBody.FORM);
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            Object val = entry.getValue();
            if (val instanceof File) {
                String fileKey = "img_name";
                formBody.addFormDataPart(fileKey, ((File) val).getName(), RequestBody.create(MediaType.parse("image/png"), (File) val));

                LogUtil.i("HttpFileTask", fileKey + "  filePath:" + ((File) val).getPath());
//                        .build();
//                RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), (File) val);
//                formBody.addPart(Headers.of("Content-Disposition",
//                        "form-data; name=" + "\"file["+((File) val).getName()+"]\";" + "filename =\"" + ((File) val).getName() + "\""), fileBody);
//
            } else {
                formBody.addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, entry.getValue().toString()));
                LogUtil.i("HttpFileTask", "key:" + key + "  val:" + entry.getValue().toString());
            }
        }

        return formBody.build();
    }

    public interface ResponseListener {
        void tryReturn(int id, Object response);
    }

}
