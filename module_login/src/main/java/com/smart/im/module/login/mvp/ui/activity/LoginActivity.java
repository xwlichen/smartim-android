package com.smart.im.module.login.mvp.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.component.DaggerAppComponent;
import com.smart.im.common.sdk.base.BaseAppActivity;
import com.smart.im.common.sdk.utils.AnimatorUtils;
import com.smart.im.common.sdk.utils.KeyBoardUtils;
import com.smart.im.common.sdk.widget.dialog.SweetAlertDialog;
import com.smart.im.module.login.R;
import com.smart.im.module.login.R2;
import com.smart.im.module.login.di.component.DaggerLoginComponent;
import com.smart.im.module.login.mvp.contract.LoginContract;
import com.smart.im.module.login.mvp.presenter.LoginPresenter;
import com.smart.im.module.login.widget.LoginBGView;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.AutoSizeUtils;

import static com.smart.im.common.sdk.utils.AnimatorUtils.ANIMATOR_ALPHA;
import static com.smart.im.common.sdk.utils.AnimatorUtils.ANIMATOR_SCALEX;
import static com.smart.im.common.sdk.utils.AnimatorUtils.ANIMATOR_SCALEY;


/**
 * @author lichen
 * @date ：2018/9/18 下午1:54
 * @email : 196003945@qq.com
 * @description :登陆页面
 */
public class LoginActivity extends BaseAppActivity <LoginPresenter> implements LoginContract.View{

    public static final long ANIMATOR_TIME = 1000;


    @BindView(R2.id.iv_head)
    ImageView iv_head;
    @BindView(R2.id.lbgv_container)
    LoginBGView lbgv_container;

    @BindView(R2.id.ll_step1)
    LinearLayout ll_step1;
    @BindView(R2.id.btn_step1_login)
    Button btn_step1_login;


    @BindView(R2.id.ll_step2)
    LinearLayout ll_step2;

    @BindView(R2.id.layout_bottom)
    View layout_bottom;
    @BindView(R2.id.fl_content)
    View fl_content;

    int bottomHeight;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
//        DaggerAppComponent
//                .builder()
//                .appComponent(appComponent)
//                .view(this)
//                .build()
//                .inject(this);

    }


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        fl_content = findViewById(R.id.fl_content);
        layout_bottom = findViewById(R.id.layout_bottom);
        KeyBoardUtils.getInstance().initKeyBorad(LoginActivity.this);
        KeyBoardUtils.getInstance().onCreate();
        KeyBoardUtils.getInstance().setOnKeyboardStatusChangeListener(onKeyBoardStatusChangeListener);


    }

    @OnClick(R2.id.btn_step1_login)
    public void loginStep1() {
        locationStepView(2);
        startAnimator();

    }

    @OnClick(R2.id.btn_step2_login)
    public void loginStep2() {


    }

    @OnClick(R2.id.iv_head)
    public void showDialog(){
        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("是否添加好友")
//                        .setContentText("Something went wrong!")
                .show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyBoardUtils.getInstance().onDestroy();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            bottomHeight = layout_bottom.getHeight();
            locationStepView(1);

        }
    }


    /**
     * 定位控件位置
     *
     * @param step 1-步骤1 2-步骤2
     */

    public void locationStepView(int step) {
        int radius = AutoSizeUtils.dp2px(LoginActivity.this, 40);
        int diameter = AutoSizeUtils.dp2px(LoginActivity.this, 80);

        float marginTop = lbgv_container.getHeightMin() + lbgv_container.getdValue() / 2 - radius;
        int marginLeft = lbgv_container.getmWidth() / 2 - radius;

        FrameLayout.LayoutParams layoutParamsContainer = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsContainer.setMargins(0, (int) (AutoSizeUtils.dp2px(LoginActivity.this, 70) + marginTop + radius), 0, 0);


        if (step == 1) {
            ll_step1.setVisibility(View.VISIBLE);
            ll_step2.setVisibility(View.GONE);
            iv_head.setVisibility(View.GONE);

            ll_step1.setLayoutParams(layoutParamsContainer);
            ll_step1.setGravity(Gravity.CENTER);
        } else if (step == 2) {
            ll_step1.setVisibility(View.GONE);
            ll_step2.setVisibility(View.VISIBLE);
            ll_step2.setAlpha(0f);
            iv_head.setVisibility(View.VISIBLE);
            iv_head.setAlpha(0f);


            FrameLayout.LayoutParams layoutParamsHead = new FrameLayout.LayoutParams(diameter, diameter);
            layoutParamsHead.setMargins(marginLeft, (int) marginTop, (int) marginLeft, 0);
            iv_head.setLayoutParams(layoutParamsHead);


            ll_step2.setLayoutParams(layoutParamsContainer);
            ll_step2.setGravity(Gravity.CENTER);
        }
    }


    /**
     * 各个控件的动画效果
     */
    public void startAnimator() {

        AnimatorUtils.goValueAnimator(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                lbgv_container.computeLocation(value);
            }
        }, ANIMATOR_TIME, 0, (lbgv_container.getdValue()));

        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(iv_head, ANIMATOR_ALPHA, 0f, 1f);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(iv_head, ANIMATOR_SCALEX, 0f, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(iv_head, ANIMATOR_SCALEY, 0f, 1f);
        Animator[] animators = {animatorAlpha, animatorScaleX, animatorScaleY};
        AnimatorUtils.goAnimatorSetWith(ANIMATOR_TIME, animators);


        AnimatorUtils.goObjectAnimator(iv_head, "alpha", ANIMATOR_TIME, 0f, 1f);
        AnimatorUtils.goObjectAnimator(ll_step2, "alpha", ANIMATOR_TIME, 0f, 1f);


    }

    private KeyBoardUtils.OnKeyboardStatusChangeListener onKeyBoardStatusChangeListener = new KeyBoardUtils.OnKeyboardStatusChangeListener() {

        @Override
        public void onKeyboardPop(int keyboardHeight) {

            final int height = keyboardHeight;
            if (bottomHeight > height) {
                layout_bottom.setVisibility(View.GONE);
            } else {
                int offset = bottomHeight - height;
                final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) fl_content
                        .getLayoutParams();
                lp.topMargin = offset;
                fl_content.setLayoutParams(lp);
            }

        }

        @Override
        public void onKeyboardClose(int keyboardHeight) {
            if (View.VISIBLE != layout_bottom.getVisibility()) {
                layout_bottom.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout_bottom.setVisibility(View.VISIBLE);
                    }
                }, 300);
            }
            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) fl_content
                    .getLayoutParams();
            if (lp.topMargin != 0) {
                lp.topMargin = 0;
                fl_content.setLayoutParams(lp);
            }

        }
    };

    @Override
    public Activity getActivity() {
        return null;
    }



}
