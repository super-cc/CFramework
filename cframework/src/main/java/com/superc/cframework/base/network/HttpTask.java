package com.superc.cframework.base.network;

/**
 * 网络任务
 * Created by Super丶C on 2017/10/30.
 */

public abstract class HttpTask<T> extends HttpAbstractTask<T> {

    /**
     * 接口地址
     * @return 接口的url地址
     */
    public abstract String getUrl();

    /**
     * query string
     * @return 接口的query string
     */
    public abstract String getQueryString();

//    /**
//     * utf-8 UrlEncode
//     *
//     * @param text
//     * @return
//     */
//    protected static String encode(String text) {
//        try {
//            return URLEncoder.encode(text, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return text;
//    }

    protected String createUrl() {
        String url = getUrl();
        if (url == null) {
            throw new IllegalStateException("url cannot be null");
        }
        String queryString = getQueryString();
        if (queryString != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(url);
            if (url.indexOf('?') == -1) {
                builder.append('?');
                if (queryString.startsWith("&")) {
                    builder.append(queryString.substring(1));
                } else {
                    builder.append(queryString);
                }
            } else {
                if (queryString.startsWith("&")) {
                    builder.append(queryString);
                } else {
                    builder.append('&').append(queryString);
                }
            }
            return builder.toString();
        }
        return url;
    }
}
