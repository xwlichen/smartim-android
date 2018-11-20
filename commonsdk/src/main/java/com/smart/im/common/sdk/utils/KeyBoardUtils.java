package com.smart.im.common.sdk.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

/**
 * @author lichen
 * @date ：2018/9/19 下午7:52
 * @email : 196003945@qq.com
 * @description :
 */
public class KeyBoardUtils {

    protected static KeyBoardUtils INSTANCE;

    public synchronized static KeyBoardUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KeyBoardUtils();
        }
        return INSTANCE;

    }


    //监听键盘弹出
    private Activity activity;
    private OnKeyboardStatusChangeListener onKeyboardStatusChangeListener;
    private int windowBottom = -1;
    private int keyboardHeight = 0;


    public void initKeyBorad(Activity activity) {
        this.activity = activity;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (activity.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onCreate() {
        View content = activity.findViewById(android.R.id.content);
        // content.addOnLayoutChangeListener(listener); 这个方法有时会出现一些问题
        content.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public void onDestroy() {
        View content = activity.findViewById(android.R.id.content);
        ViewUtils.removeOnGlobalLayoutListener(content, onGlobalLayoutListener);
    }


    //监听视图树布局繁盛变化
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            //获取windown的可见区域
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            Log.d("KeyboardHelper", "onGlobalLayout: " + rect + ", " + windowBottom);
            int newBottom = rect.bottom;
            if (windowBottom != -1 && windowBottom != newBottom) {
                if (newBottom < windowBottom) {
                    // keyboard pop
                    keyboardHeight = windowBottom - newBottom;
                    if (onKeyboardStatusChangeListener != null) {
                        onKeyboardStatusChangeListener.onKeyboardPop(keyboardHeight);
                    }
                } else {
                    // keyboard close
                    if (onKeyboardStatusChangeListener != null) {
                        onKeyboardStatusChangeListener.onKeyboardClose(keyboardHeight);
                    }
                }
            }
            windowBottom = newBottom;
        }
    };

    public void setOnKeyboardStatusChangeListener(
            OnKeyboardStatusChangeListener onKeyboardStatusChangeListener) {
        this.onKeyboardStatusChangeListener = onKeyboardStatusChangeListener;
    }

    public interface OnKeyboardStatusChangeListener {

        void onKeyboardPop(int keyboardHeight);

        void onKeyboardClose(int keyboardHeight);
    }

    //监听键盘弹出

}
