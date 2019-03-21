package com.smart.im.common.core.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author lichen
 * @date ：2018/11/9 下午5:44
 * @email : 196003945@qq.com
 * @description :实体基类：实现序列化
 */
public class Result<T> implements Serializable {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String msg;
    @SerializedName("data")
    private T data;


    public boolean OK() {
        return code == 2000;
    }

    public boolean isTokenExpired() {
        return code == 20006;
    }
//    public boolean isRedirect() {
//        return code == 20036;
//    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
