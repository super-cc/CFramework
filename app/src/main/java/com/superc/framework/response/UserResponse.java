package com.superc.framework.response;

import com.superc.framework.network.HttpResponse;

/**
 * 创建日期：2017/10/30 on 16:00
 * 描述：网络返回的Json用到的类，GsonFormat插件来自自动生成代码
 * 作者：郭士超
 * QQ：1169380200
 */

public class UserResponse extends HttpResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private String userId;
        private String token;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }

}
