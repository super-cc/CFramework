package com.superc.cframework.utils;

/**
 * utf-8和unicode汉字码转换器
 * Created by Super丶C on 2017/10/30.
 */

public class UnicodeUtil {
    /**
     * 将utf-8的汉字转换成unicode格式汉字码
     * @param string
     * @return
     */
    public static String stringToUnicode(String string) {

        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        String str = unicode.toString();

        return str.replaceAll("\\\\", "0x");
    }

    /**
     * 将unicode的汉字码转换成utf-8格式的汉字
     * @param unicode
     * @return
     */
    public static String unicodeToString(String unicode) {
//        try {
//            return  URLDecoder.decode(unicode,"utf-8");
            return unicode;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "error";
//        String str = unicode.replace("0x", "\\");
//
//        StringBuffer string = new StringBuffer();
//        String[] hex = str.split("\\\\u");
//        for (int i = 1; i < hex.length; i++) {
//            int data = Integer.parseInt(hex[i], 16);
//            string.append((char) data);
//        }
//        return string.toString();
    }

}
