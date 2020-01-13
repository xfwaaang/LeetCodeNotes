package com.xf.utils;

import java.util.Arrays;

/**
 * @author xfwaaang
 * @create 2019-09-05 17:00
 */
public class Utils {
    public static void println(Object obj){
        System.out.println(toString(obj));
    }

    /**
     * 将（数组）对象转成字符串
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        if(obj == null)    return "";

        String res;
        if (obj instanceof int[]){
            res = Arrays.toString((int[])obj);
        }else if (obj instanceof String[]){
            res = Arrays.toString((String[])obj);
        }else {
            res = obj.toString();
        }
        return res;
    }
}
