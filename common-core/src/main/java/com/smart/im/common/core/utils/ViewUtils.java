package com.smart.im.common.core.utils;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * @author lichen
 * @date ：2018/9/19 下午7:55
 * @email : 196003945@qq.com
 * @description :
 */
public class ViewUtils {


    /**
     * 删除
     * @param view
     * @param onGlobalLayoutListener
     */
    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }


}
