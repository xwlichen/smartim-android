/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smart.im.module.login.mvp.presenter;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.smart.im.common.core.model.Result;
import com.smart.im.common.core.rx.DefaultErrorHandlerSubscriber;
import com.smart.im.common.core.rx.RxDialogUtils;
import com.smart.im.module.login.mvp.contract.LoginContract;
import com.smart.im.module.login.mvp.model.entity.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;




/**
 * @author lichen
 * @date ：2018/11/9 下午7:34
 * @email : 196003945@qq.com
 * @description :LoginPresenter逻辑处理
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    AppManager mAppManager;
    @Inject
    Application mApplication;



    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 使用 2017 Google IO 发布的 Architecture Components 中的 Lifecycles 的新特性 (此特性已被加入 Support library)
     * 使 {@code Presenter} 可以与 {@link SupportActivity} 和 {@link Fragment} 的部分生命周期绑定
     */
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    void onCreate() {
//        requestDatas();//打开 App 时自动加载列表
//    }

    public void requestDatas() {

        mModel.login("7", "1")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxDialogUtils.applySchedulers(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new DefaultErrorHandlerSubscriber<Result<User>>(mErrorHandler) {
                    @Override
                    public void onSucess(Result<User> userResult) {

                    }
                });





    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
