package com.smart.im.common.sdk.rx;

import android.text.TextUtils;

import com.hjq.toast.ToastUtils;
import com.smart.im.common.sdk.model.Result;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * @author lichen
 * @date ：2018/11/9 下午5:40
 * @email : 196003945@qq.com
 * @description :
 */
public abstract class DefaultErrorHandlerSubscriber<T> extends ErrorHandleSubscriber<T> {
    public DefaultErrorHandlerSubscriber(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
    }

    @Override
    public void onNext(T t) {
        if (t instanceof Result) {
            try {
                if (((Result) t).OK()) {
                    onSucess(t);
                } else {
                    onFail(((Result) t).getMsg());
                }
            } catch (Exception e) {
                onFail(e.toString());

            }

        } else {
            onSucess(t);
        }

    }


    public abstract void onSucess(T t);


    public void onFail(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtils.show(msg);
        }

    }
}
