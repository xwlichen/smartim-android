package com.smart.im.main.di.compoent;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.smart.im.main.di.module.MainModule;
import com.smart.im.main.mvp.contract.MainContract;
import com.smart.im.main.mvp.ui.activity.MainActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * @author lichen
 * @date ：2018/12/10 下午10:45
 * @email : 196003945@qq.com
 * @description :
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainComponent.Builder view(MainContract.View view);

        MainComponent.Builder appComponent(AppComponent appComponent);

        MainComponent build();
    }
}