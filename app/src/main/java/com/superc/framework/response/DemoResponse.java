package com.superc.framework.response;

import com.superc.framework.network.HttpResponse;

/**
 * 创建日期：2017/11/1 on 9:29
 * 描述：一个网络用的Demo
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoResponse extends HttpResponse {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }
}
