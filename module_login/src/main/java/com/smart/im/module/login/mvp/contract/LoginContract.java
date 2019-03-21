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
package com.smart.im.module.login.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.smart.im.common.core.model.Result;
import com.smart.im.module.login.mvp.model.entity.User;

import io.reactivex.Observable;


/**
 * @author lichen
 * @date ：2018/11/9 下午7:34
 * @email : 196003945@qq.com
 * @description :login  页面的接口定义
 */
public interface LoginContract {
    interface View extends IView {
        Activity getActivity();
    }
    interface Model extends IModel {
        Observable<Result<User>> login(String account, String password);
    }
}
