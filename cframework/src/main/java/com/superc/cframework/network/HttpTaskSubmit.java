package com.superc.cframework.network;

import com.superc.cframework.base.network.HttpAbstractTask;
import com.superc.cframework.base.network.HttpTask;

/**
 * 执行task
 * Created by Super丶C on 2017/10/30.
 */

public class HttpTaskSubmit {
    /**
     * 执行task
     *
     * @param task HttpGet,HttpPost,HttpUpload的子类
     */
    public static void executeTask(HttpTask<?> task, HttpAbstractTask.OnResponseCallback callback) {
        if (task == null) {
            return;
        }
        if (!task.hasCallback()) {
            task.setOnResponseCallback(callback);
        }
        task.execute();
    }

    /**
     * 立即执行task
     *
     * @param task HttpGet,HttpPost,HttpUpload的子类
     */
    public static void executeTaskNow(HttpTask<?> task, HttpAbstractTask.OnResponseCallback callback) {
        if (task == null) {
            return;
        }
        if (!task.hasCallback()) {
            task.setOnResponseCallback(callback);
        }
        task.executeNow();
    }
}
