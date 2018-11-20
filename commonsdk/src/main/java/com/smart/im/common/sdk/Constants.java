package com.smart.im.common.sdk;

/**
 * ================================================
 * CommonSDK 的 Constants 可以定义公用的常量, 比如关于业务的常量或者正则表达式, 每个组件的 Constants 可以定义组件自己的私有常量
 * <p>
 * Created by JessYan on 30/03/2018 17:32
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Constants {

    //电话号码正则
    String PHONE_REGULAR = "^1[3-9]\\d{9}$";


    //token
    String TOKEN = "token";
    //渠道 Android 、ios
    String PLATFORM="platform";
    //设备id
    String DEVICE_ID="device_id";
    //应用版本
    String VERSION="version";



    //修改

    /**
     * com.jess.arms.mvp.IVIew
     * com.jess.arms.base.BaseActivity
     * com.jess.arms.utils.ThirdViewUtil
     * com.jess.arms.base.BaseHolder
     */

}
