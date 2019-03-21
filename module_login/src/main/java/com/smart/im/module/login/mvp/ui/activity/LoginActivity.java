package com.smart.im.module.login.mvp.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
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
import com.smart.im.common.core.base.BaseAppActivity;
import com.smart.im.common.core.utils.AnimatorUtils;
import com.smart.im.common.core.utils.KeyBoardUtils;
import com.smart.im.common.core.widget.dialog.SweetAlertDialog;
import com.smart.im.module.login.R;
import com.smart.im.module.login.R2;
import com.smart.im.module.login.di.component.DaggerLoginComponent;
import com.smart.im.module.login.mvp.contract.LoginContract;
import com.smart.im.module.login.mvp.presenter.LoginPresenter;
import com.smart.im.module.login.widget.LoginBGView;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.autosize.utils.AutoSizeUtils;

import static com.smart.im.common.core.utils.AnimatorUtils.ANIMATOR_ALPHA;
import static com.smart.im.common.core.utils.AnimatorUtils.ANIMATOR_SCALEX;
import static com.smart.im.common.core.utils.AnimatorUtils.ANIMATOR_SCALEY;


/**
 * @author lichen
 * @date ：2018/9/18 下午1:54
 * @email : 196003945@qq.com
 * @description :登陆页面
 */
public class LoginActivity extends BaseAppActivity <LoginPresenter> implements LoginContract.View{

    public static final long ANIMATOR_TIME = 1000;


    @BindView(R2.id.ivHead)
    ImageView ivHead;
    @BindView(R2.id.lbgvContainer)
    LoginBGView lbgvContainer;

    @BindView(R2.id.llStep1)
    LinearLayout llStep1;
    @BindView(R2.id.btnStep1Login)
    Button btnStep1Login;


    @BindView(R2.id.llStep2)
    LinearLayout llStep2;

    @BindView(R2.id.layoutBottom)
    View layoutBottom;
    @BindView(R2.id.flContent)
    View flContent;

    int bottomHeight;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.login_activity;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        flContent = findViewById(R.id.flContent);
        layoutBottom = findViewById(R.id.layoutBottom);
        KeyBoardUtils.getInstance().initKeyBorad(LoginActivity.this);
        KeyBoardUtils.getInstance().onCreate();
        KeyBoardUtils.getInstance().setOnKeyboardStatusChangeListener(onKeyBoardStatusChangeListener);


    }

    @OnClick(R2.id.btnStep1Login)
    public void loginStep1() {
        locationStepView(2);
        startAnimator();

    }

    @OnClick(R2.id.btn_step2_login)
    public void loginStep2() {

        mPresenter.requestDatas();
    }

    @OnClick(R2.id.ivHead)
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
            bottomHeight = layoutBottom.getHeight();
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

        float marginTop = lbgvContainer.getHeightMin() + lbgvContainer.getdValue() / 2 - radius;
        int marginLeft = lbgvContainer.getmWidth() / 2 - radius;

        FrameLayout.LayoutParams layoutParamsContainer = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsContainer.setMargins(0, (int) (AutoSizeUtils.dp2px(LoginActivity.this, 70) + marginTop + radius), 0, 0);


        if (step == 1) {
            llStep1.setVisibility(View.VISIBLE);
            llStep2.setVisibility(View.GONE);
            ivHead.setVisibility(View.GONE);

            llStep1.setLayoutParams(layoutParamsContainer);
            llStep1.setGravity(Gravity.CENTER);
        } else if (step == 2) {
            llStep1.setVisibility(View.GONE);
            llStep2.setVisibility(View.VISIBLE);
            llStep2.setAlpha(0f);
            ivHead.setVisibility(View.VISIBLE);
            ivHead.setAlpha(0f);


            FrameLayout.LayoutParams layoutParamsHead = new FrameLayout.LayoutParams(diameter, diameter);
            layoutParamsHead.setMargins(marginLeft, (int) marginTop, (int) marginLeft, 0);
            ivHead.setLayoutParams(layoutParamsHead);


            llStep2.setLayoutParams(layoutParamsContainer);
            llStep2.setGravity(Gravity.CENTER);
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
                lbgvContainer.computeLocation(value);
            }
        }, ANIMATOR_TIME, 0, (lbgvContainer.getdValue()));

        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(ivHead, ANIMATOR_ALPHA, 0f, 1f);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(ivHead, ANIMATOR_SCALEX, 0f, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(ivHead, ANIMATOR_SCALEY, 0f, 1f);
        Animator[] animators = {animatorAlpha, animatorScaleX, animatorScaleY};
        AnimatorUtils.goAnimatorSetWith(ANIMATOR_TIME, animators);


        AnimatorUtils.goObjectAnimator(ivHead, "alpha", ANIMATOR_TIME, 0f, 1f);
        AnimatorUtils.goObjectAnimator(llStep2, "alpha", ANIMATOR_TIME, 0f, 1f);


    }

    private KeyBoardUtils.OnKeyboardStatusChangeListener onKeyBoardStatusChangeListener = new KeyBoardUtils.OnKeyboardStatusChangeListener() {

        @Override
        public void onKeyboardPop(int keyboardHeight) {

            final int height = keyboardHeight;
            if (bottomHeight > height) {
                layoutBottom.setVisibility(View.GONE);
            } else {
                int offset = bottomHeight - height;
                final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) flContent
                        .getLayoutParams();
                lp.topMargin = offset;
                flContent.setLayoutParams(lp);
            }

        }

        @Override
        public void onKeyboardClose(int keyboardHeight) {
            if (View.VISIBLE != layoutBottom.getVisibility()) {
                layoutBottom.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layoutBottom.setVisibility(View.VISIBLE);
                    }
                }, 300);
            }
            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) flContent
                    .getLayoutParams();
            if (lp.topMargin != 0) {
                lp.topMargin = 0;
                flContent.setLayoutParams(lp);
            }

        }
    };

    @Override
    public Activity getActivity() {
        return null;
    }



}
