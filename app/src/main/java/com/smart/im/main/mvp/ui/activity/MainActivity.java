package com.smart.im.main.mvp.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.jess.arms.di.component.AppComponent;
import com.smart.im.common.sdk.base.BaseAppActivity;
import com.smart.im.main.LoginUtils;
import com.smart.im.main.R;
import com.smart.im.main.di.compoent.DaggerMainComponent;
import com.smart.im.main.mvp.contract.MainContract;
import com.smart.im.main.mvp.presenter.MainPresenter;

import butterknife.BindView;

import static com.smart.im.main.LoginUtils.info;
import static com.smart.im.main.LoginUtils.warn;

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

        LoginUtils.info("ClientSDK", "指纹为fsdfdsfdsfdsfdsf 回家回家回家回家好好回家回家回家回家回家减肥药她的头有点痛有点讨厌冬天有冬天讨厌冬天的太阳太阳都讨厌的一天");
        LoginUtils.warn("ClientSDK", "指纹为fsdfdsfdsfdsfdsf");
        LoginUtils.error("ClientSDK", "指纹为fsdfdsfdsfdsfdsf");


        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CCResult result = null;
                CC cc = null;
//                Utils.navigation(MainActivity.this, RouterHub.ZHIHU_HOMEACTIVITY);
                cc = CC.obtainBuilder("module_login.login")
                        .setActionName("showActivityA")
                        .build();
                result = cc.call();
            }
        });
    }

    @Override
    public Activity getActivity() {
        return null;
    }
}
