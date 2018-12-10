package com.smart.im.main.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.jess.arms.di.component.AppComponent;
import com.smart.im.common.sdk.base.BaseAppActivity;
import com.smart.im.main.R;
import com.smart.im.main.di.compoent.DaggerMainComponent;
import com.smart.im.main.mvp.contract.MainContract;
import com.smart.im.main.mvp.presenter.MainPresenter;

import butterknife.BindView;

/**
 * @author lichen
 * @date ：2018/12/10 下午10:04
 * @email : 196003945@qq.com
 * @description :
 */
public class MainActivity extends BaseAppActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public Activity getActivity() {
        return null;
    }
}
