package com.superc.framework.model;

/**
 * 创建日期：2017/10/31 on 16:47
 * 描述：在ListView或RecyclerView中使用的数据模型
 * 作者：郭士超
 * QQ：1169380200
 */

public class DemoItem {

    private String name;
    private String age;

    public DemoItem(String name, String age) {
        this.name = name;
        this.age = age + "岁";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
