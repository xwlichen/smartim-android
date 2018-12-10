package com.smart.im.main.mvp.contract;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

/**
 * @author lichen
 * @date ：2018/12/10 下午10:03
 * @email : 196003945@qq.com
 * @description :main 界面的接口定义
 */
public interface MainContract {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        Activity getActivity();
    }

    interface Model extends IModel {
        List<Fragment> getFragments();
    }

}
