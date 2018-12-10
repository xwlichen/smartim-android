package com.smart.im.main.di.module;

import com.smart.im.main.mvp.contract.MainContract;
import com.smart.im.main.mvp.model.MainModel;

import dagger.Binds;
import dagger.Module;

/**
 * @author lichen
 * @date ：2018/12/10 下午10:26
 * @email : 196003945@qq.com
 * @description ;提供所需的实例等
 */

@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);
}
