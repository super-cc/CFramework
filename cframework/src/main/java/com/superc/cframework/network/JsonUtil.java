package com.superc.cframework.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 检测是否为Json
 * Created by Super丶C on 2017/10/30.
 */

public class JsonUtil {
    public static boolean isJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonObject = null;
            return true;
        } catch (JSONException e) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                jsonArray = null;
                return true;
            } catch (JSONException e1) {
                return false;
            }
        }
    }
}
