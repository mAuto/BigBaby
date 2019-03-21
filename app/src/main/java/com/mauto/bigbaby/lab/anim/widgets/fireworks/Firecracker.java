package com.mauto.bigbaby.lab.anim.widgets.fireworks;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mauto.bigbaby.R;

/**
 * Created by haohuidong on 19-1-29.
 */

public class Firecracker extends View {
    public Firecracker(Context context) {
        super(context);
        Log.e(">>>>>>", "Firecracker --> " + "Firecracker"+" 0");
        init();
    }

    public Firecracker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(">>>>>>", "Firecracker --> " + "Firecracker"+" 1");
        initOptions(context, attrs);
        init();
    }

    public Firecracker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(">>>>>>", "Firecracker --> " + "Firecracker"+" 2");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mMaxRadius = Math.min(width, height) / 2 - 5;
        mCenterX = width / 2;
        mCenterY = height / 2;

        Log.e(">>>>>>", "Firecracker --> " + "onMeasure"+" >>>>>>>>>>>>>>>>>>>>>>>>");
        Log.e(">>>>>>", "Firecracker --> " + "onMeasure"+" "+mMaxRadius);
        Log.e(">>>>>>", "Firecracker --> " + "onMeasure"+" "+mCenterX+" "+mCenterY);
        Log.e(">>>>>>", "Firecracker --> " + "onMeasure"+" <<<<<<<<<<<<<<<<<<<<<<<<");
    }

    //region init constants
    /*
    * 19-1-29 下午5:52
    * */
    /////////////////////////////////////↓↓↓ --> init constants <-- ↓↓↓/////////////////////////////////////
    private Paint mBigPaint;
    private Paint mSecondaryPaint;
    static int colorRandom[] = new int[10];
    private int mMaxRadius, mCenterX, mCenterY;
    private int mWidth = 30;

    private void init() {
        Log.e(">>>>>>", "Firecracker --> " + "init"+"");
        mBigPaint = new Paint();
//        mBigPaint.setAntiAlias(true);
        mBigPaint.setColor(mFireColor);
        mBigPaint.setStyle(Paint.Style.FILL);
        mBigPaint.setStrokeWidth(1);
        mBigPaint.setStrokeCap(Paint.Cap.ROUND);

        mSecondaryPaint = new Paint();
//        mSecondaryPaint.setAntiAlias(true);
        mSecondaryPaint.setColor(Color.WHITE);
        mSecondaryPaint.setStyle(Paint.Style.FILL);
        mSecondaryPaint.setStrokeWidth(1);
        mSecondaryPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private int mFireColor = 0;
    private int mDuration = 500;
    private boolean mShining = false;
    private static long FRAME_REFRESH_DELAY = 25;

    private void initOptions(Context context, AttributeSet attrs) {
        ValueAnimator.setFrameDelay(FRAME_REFRESH_DELAY);
        TypedArray firecrackerAttrs = context.obtainStyledAttributes(attrs, R.styleable.Firecracker);
        mFireColor = firecrackerAttrs.getColor(R.styleable.Firecracker_fire_color, Color.CYAN);
        mDuration = firecrackerAttrs.getInt(R.styleable.Firecracker_fire_duration, 500);
        mShining = firecrackerAttrs.getBoolean(R.styleable.Firecracker_fire_flashing, false);
    }

    private ValueAnimator valueAnimator;
    /////////////////////////////////////↑↑↑ --> init constants <-- ↑↑↑/////////////////////////////////////
    //endregion

    //region api
    /*
    * 19-1-29 下午6:28
    * */
    /////////////////////////////////////↓↓↓ --> api <-- ↓↓↓/////////////////////////////////////
    private int mBigCircleWidth, mSecondaryWidth;
    public void startAnim() {
        valueAnimator = ValueAnimator.ofInt(0, mMaxRadius).setDuration(mDuration);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int radius = (int) animation.getAnimatedValue();
                mBigCircleWidth = radius;

                if (mBigCircleWidth >= mMaxRadius / 3) {
                    mSecondaryWidth = (mBigCircleWidth - mMaxRadius / 3) * 3 / 2;
                }

                if (radius >= mMaxRadius) {
                    mBigCircleWidth = 0;
                    mSecondaryWidth = 0;
                }

                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCenterX, mCenterX, mBigCircleWidth, mBigPaint);
        canvas.drawCircle(mCenterX, mCenterX, mSecondaryWidth, mSecondaryPaint);
    }
    /////////////////////////////////////↑↑↑ --> api <-- ↑↑↑/////////////////////////////////////
    //endregion
}
