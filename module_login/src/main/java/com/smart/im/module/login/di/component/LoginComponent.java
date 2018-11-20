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
package com.smart.im.module.login.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.smart.im.module.login.di.module.LoginModule;
import com.smart.im.module.login.mvp.contract.LoginContract;
import com.smart.im.module.login.mvp.ui.activity.LoginActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @author lichen
 * @date ：2018/11/9 下午7:34
 * @email : 196003945@qq.com
 * @description :
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        LoginComponent.Builder view(LoginContract.View view);
        LoginComponent.Builder appComponent(AppComponent appComponent);
        LoginComponent build();
    }
}
