package com.smart.im.main.mvp.presenter;


import android.support.v4.app.Fragment;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.smart.im.main.mvp.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author lichen
 * @date ：2018/12/10 下午10:05
 * @email : 196003945@qq.com
 * @description :Main 界面的逻辑处理
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {


    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View view) {
        super(model, view);
    }


    public List<Fragment> provideFragment() {

        return mModel.getFragments();

    }
}
