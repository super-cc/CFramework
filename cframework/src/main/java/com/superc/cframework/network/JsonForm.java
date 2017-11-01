package com.superc.cframework.network;

/**
 * JsonForm
 * Created by Super丶C on 2017/10/30.
 */

public class JsonForm<T> {
    public static <T> T formJson(String json, Class<T> tClass) {
        return GsonUtil.getGson().fromJson(json, tClass);
    }
}
