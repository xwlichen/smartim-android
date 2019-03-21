package com.smart.im.common.core.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * @author lichen
 * @date ：2018/9/27 下午3:31
 * @email : 196003945@qq.com
 * @description :动画工具类
 */
public class AnimatorUtils {

    public static final String ANIMATOR_ALPHA="alpha";                  //透明度
    public static final String ANIMATOR_SCALEX="scaleX";                //沿x轴拉伸
    public static final String ANIMATOR_SCALEY="scaleY";                //沿y轴拉伸
    public static final String ANIMATOR_TRANSX="translationX";          //沿x轴平移
    public static final String ANIMATOR_TRANSY="translationY";          //沿y轴平移





    /**
     * 属性动画
     * @param view     目标view
     * @param effect   效果 如：ANIMATOR_ALPHA
     * @param duration 时间
     * @param values   值
     */
    public static void goObjectAnimator(View view,String effect,long duration,float... values) {
        ObjectAnimator animatorObject = ObjectAnimator.ofFloat(view, ANIMATOR_ALPHA, values);
        animatorObject.setDuration(duration);

        animatorObject.start();
    }


    /**
     * 值动画
     * @param listener 监听
     * @param duration 时间
     * @param values   值
     */
    public static void goValueAnimator(ValueAnimator.AnimatorUpdateListener listener,long duration,float... values){
        ValueAnimator animatorValue = ValueAnimator.ofFloat(values);
        animatorValue.setDuration(duration);
        animatorValue.addUpdateListener(listener);

        animatorValue.start();
    }


    /**
     * 同步组合多个动画
     * @param duration
     * @param animators
     */
    public static  void goAnimatorSetWith(long duration, Animator[] animators){
        AnimatorSet animatorSet = new AnimatorSet();
        if (!ValueUtils.isEmptyOfArray(animators)){
            animatorSet.play(animators[0]);
            if (animators.length>=2){
                for (int i=1;i<animators.length;i++){
                    animatorSet.play(animators[0]).with(animators[i]);
                }
            }
        }
        animatorSet.setDuration(duration);
        animatorSet.start();

    }

    //        animatorSet.play(animators[0]).with(scaleYAnimator).with(animator).before(translationXAnimator);


}
