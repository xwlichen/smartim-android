package com.smart.im.common.core.utils;

/**
 * @author lichen
 * @date ：2018/9/27 下午4:14
 * @email : 196003945@qq.com
 * @description :值判空类
 */
public class ValueUtils {


    /**
     * 判断数组是否为空
     * @param array
     * @return
     */
    public static boolean isEmptyOfArray(Object[] array) {
        if (array == null) {
            return true;
        }

        if (array.length == 0) {
            return true;
        }

        return false;


    }
}
