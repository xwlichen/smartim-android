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
package com.smart.im.module.login.mvp.model.api.service;

import com.smart.im.common.sdk.model.Result;
import com.smart.im.module.login.mvp.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.smart.im.module.login.app.Api.LOGIN_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @author lichen
 * @date ：2018/11/14 下午7:40
 * @email : 196003945@qq.com
 * @description :
 */
public interface LoginService {
    /**
     * 登录
     */
    @Headers({DOMAIN_NAME_HEADER + LOGIN_DOMAIN_NAME})
    @FormUrlEncoded
    @POST("api/user/login")
    Observable<Result<User>> login(@Field("account") String account,
                                   @Field("password") String password);

//    @Headers({DOMAIN_NAME_HEADER + LOGIN_DOMAIN_NAME})
//    @FormUrlEncoded
//    @POST("app/v3/user/login")
//    Observable<Result<User>> login(@Field("telephone") String account,
//                                   @Field("password") String password);
}
