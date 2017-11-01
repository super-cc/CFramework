package com.superc.cframework.network;

import com.google.gson.Gson;

/**
 * Gson工具类
 * Created by Super丶C on 2017/10/30.
 */

public class GsonUtil {

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}
