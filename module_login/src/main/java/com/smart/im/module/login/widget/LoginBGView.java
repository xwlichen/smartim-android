package com.smart.im.module.login.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author lichen
 * @date ：2018/9/25 下午7:07
 * @email : 196003945@qq.com
 * @description :登陆页面的背景
 */
public class LoginBGView extends View {


    private Paint paintEchelon;
    private Paint paintTriangle;

    private int color_bule_deep = 0xff3448A1;
    private int color_bule_simple = 0xff1F92E8;
    private Path pathEchelon;
    private Path pathTriangle;


    private int mWidth;
    private int mHeight;

    public float heightMax = 0;
    public float heightMin = 0;
    public float dValue = 0;

    private float tranigleLocationX1 = 0;
    private float tranigleLocationY1 = 0;
    private float tranigleLocationX2 = 0;
    private float tranigleLocationY2 = 0;
    private float tranigleLocationX3 = 0;
    private float tranigleLocationY3 = 0;


    private float enclonLocationX1 = 0;
    private float enclonLocationY1 = 0;
    private float enclonLocationX2 = 0;
    private float enclonLocationY2 = 0;
    private float enclonLocationX3 = 0;
    private float enclonLocationY3 = 0;
    private float enclonLocationX4 = 0;
    private float enclonLocationY4 = 0;


    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public float getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(float heightMax) {
        this.heightMax = heightMax;
    }

    public float getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(float heightMin) {
        this.heightMin = heightMin;
    }

    public float getdValue() {
        return dValue;
    }

    public void setdValue(float dValue) {
        this.dValue = dValue;
    }

    public LoginBGView(Context context) {
        super(context);
        initParams();
    }

    public LoginBGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public LoginBGView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = measure(widthMeasureSpec);
        mHeight = measure(heightMeasureSpec);

        heightMax = mWidth / 3*2;
        heightMin = mWidth / 3;
        dValue = heightMax - heightMin;

        initLoaction();

        setMeasuredDimension(mWidth, mHeight);


    }


    /**
     * 根据Model返回值
     *
     * @param value
     * @return
     */
    private int measure(int value) {
        int result = 0;
        int specMode = MeasureSpec.getMode(value);
        int specSize = MeasureSpec.getSize(value);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result = specSize;
                break;
        }

        return result;
    }

    public void initParams() {

        paintTriangle = new Paint();
        paintTriangle = new Paint();
        paintTriangle.setColor(color_bule_deep);
        paintTriangle.setStrokeWidth(1);
        paintTriangle.setStyle(Paint.Style.FILL_AND_STROKE);
        paintTriangle.setAntiAlias(true);


        paintEchelon = new Paint();
        paintEchelon.setColor(color_bule_simple);
        paintEchelon.setStrokeWidth(1);
        paintEchelon.setStyle(Paint.Style.FILL_AND_STROKE);
        paintEchelon.setAntiAlias(true);


        pathEchelon = new Path();
        pathTriangle = new Path();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawEchelon(canvas);
        drawTranigle(canvas);
    }


    public void initLoaction() {
        if(enclonLocationY3<heightMax) {
            tranigleLocationX1 = 0;
            tranigleLocationY1 = 0;
            tranigleLocationX2 = mWidth / 2;
            tranigleLocationY2 = heightMin + dValue / 2;
            tranigleLocationX3 = 0;
            tranigleLocationY3 = heightMax;


            enclonLocationX1 = 0;
            enclonLocationY1 = 0;
            enclonLocationX2 = mWidth;
            enclonLocationY2 = 0;
            enclonLocationX3 = mWidth;
            enclonLocationY3 = heightMin;
            enclonLocationX4 = 0;
            enclonLocationY4 = heightMax;
        }
    }


    /**
     * 绘制三角行
     *
     * @param canvas
     */
    public void drawTranigle(Canvas canvas) {
        pathTriangle.reset();
        pathTriangle.moveTo(tranigleLocationX1, tranigleLocationY1);
        pathTriangle.lineTo(tranigleLocationX2, tranigleLocationY2);
        pathTriangle.lineTo(tranigleLocationX3, tranigleLocationY3);
        pathTriangle.close();


        canvas.drawPath(pathTriangle, paintTriangle);
    }

    /**
     * 绘制梯形
     *
     * @param canvas
     */
    public void drawEchelon(Canvas canvas) {
        pathEchelon.reset();
        pathEchelon.moveTo(enclonLocationX1, enclonLocationY1);
        pathEchelon.lineTo(enclonLocationX2, enclonLocationY2);
        pathEchelon.lineTo(enclonLocationX3, enclonLocationY3);
        pathEchelon.lineTo(enclonLocationX4, enclonLocationY4);
        pathEchelon.close();


        canvas.drawPath(pathEchelon, paintEchelon);
    }


    /**
     * 根据外部的属性动画值来确定坐标
     *
     * @param value
     */
    public void computeLocation(float value) {
        enclonLocationX3 = mWidth;
        enclonLocationY3 = heightMin + value;
        enclonLocationX4 = 0;
        enclonLocationY4 = heightMax - value;

        tranigleLocationY1 = value;
        tranigleLocationY3 = heightMax - value;


//        if (value<=(dValue/2)) {
//            tranigleLocationY1 = value*2;
//            tranigleLocationY3 = heightMax - value*2;
//        }else {
//
//        }

        invalidate();

    }
}
