package com.techno.fabcount;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;


public class FABCount extends FloatingActionButton {



    private static final int TOP_LEFT = 1;
    private static final int TOP_RIGHT = 2;
    private static final int BOTTOM_LEFT = 3;
    private static final int BOTTOM_RIGHT = 4;


    private static final int MAX_COUNT = 99;
    private static final String MAX_COUNT_TEXT = "99+";
    private static int TEXT_SIZE_DP = 15;
    private static final int TEXT_PADDING_DP = 2;
    private static final int MASK_COLOR = Color.parseColor("#33000000"); // Translucent black as mask color
    private static final Interpolator ANIMATION_INTERPOLATOR = new OvershootInterpolator();

    private Rect mContentBounds;
    private final Paint mTextPaint;
    private float mTextSize;
    private Paint mCirclePaint;
    private Rect mCircleBounds;
    private Paint mMaskPaint;
    private final int mAnimationDuration;
    private float mAnimationFactor;
    private int mDirection = 2;
    private int mCount;
    private String mText;
    private float mTextHeight;
    private ObjectAnimator mAnimator;

    public FABCount(Context context) {
        this(context, null, 0);
    }

    public FABCount(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FABCount(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUseCompatPadding(true);

        final float density = getResources().getDisplayMetrics().density;

        mTextSize = TEXT_SIZE_DP * density;
        float textPadding = TEXT_PADDING_DP * density;

        mAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        mAnimationFactor = 1;

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTypeface(Typeface.SANS_SERIF);
        createCircle();


    }
    void createCircle(){
        final float density = getResources().getDisplayMetrics().density;
        float textPadding = TEXT_PADDING_DP * density;
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        ColorStateList colorStateList = getBackgroundTintList();
        if (colorStateList != null) {
            mCirclePaint.setColor(colorStateList.getDefaultColor());
        } else {
            Drawable background = getBackground();
            if (background instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) background;
                mCirclePaint.setColor(colorDrawable.getColor());
            }
        }

        mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaskPaint.setStyle(Paint.Style.FILL);
        mMaskPaint.setColor(MASK_COLOR);

        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(MAX_COUNT_TEXT, 0, MAX_COUNT_TEXT.length(), textBounds);
        mTextHeight = textBounds.height();

        float textWidth = mTextPaint.measureText(MAX_COUNT_TEXT);
        float circleRadius = Math.max(textWidth, mTextHeight) / 2f + textPadding;
        mCircleBounds = new Rect(0, 0, (int) (circleRadius * 2), (int) (circleRadius * 2));
//        mCircleBounds = new Rect(0, 0, (int) (circleRadius * 2), (int) (circleRadius * 2));
        mContentBounds = new Rect();

        onCountChanged();
    }


    private final Property<FABCount, Float> ANIMATION_PROPERTY =
            new Property<FABCount, Float>(Float.class, "animation") {

                @Override
                public void set(FABCount object, Float value) {
                    mAnimationFactor = value;
                    postInvalidateOnAnimation();
                }

                @Override
                public Float get(FABCount object) {
                    return 0f;
                }
            };

    public int getCount() {
        return mCount;
    }


    public void setCount(int count) {
        //if (count == mCount) return;
        mCount = count > 0 ? count : 0;
        onCountChanged();
    }


    public void increase() {
        setCount(mCount + 1);
    }


    public void decrease() {
        setCount(mCount > 0 ? mCount - 1 : 0);
    }

    private void onCountChanged() {
        if (mCount > MAX_COUNT) {
            mText = String.valueOf(MAX_COUNT_TEXT);
        } else {
            mText = String.valueOf(mCount);
        }
    }

    private void startAnimation() {
        float start = 0f;
        float end = 1f;
        if (mCount == 0) {
            start = 1f;
            end = 0f;
        }
        if (isAnimating()) {
            mAnimator.cancel();
        }
        mAnimator = ObjectAnimator.ofObject(this, ANIMATION_PROPERTY, null, start, end);
        mAnimator.setInterpolator(ANIMATION_INTERPOLATOR);
        mAnimator.setDuration(mAnimationDuration);
        mAnimator.start();
    }

    private boolean isAnimating() {
        return mAnimator != null && mAnimator.isRunning();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCount > 0 || isAnimating()) {
            if (getContentRect(mContentBounds)) {
                mCircleBounds.offsetTo(mContentBounds.left + mContentBounds.width() - mCircleBounds.width(), mContentBounds.top);
            }

            switch (mDirection){
                case 2:
                    mCircleBounds.offsetTo(mContentBounds.left + mContentBounds.width() - mCircleBounds.width(), mContentBounds.top);
                    Log.d("hhhhhhhhhhhhh", "onDraw: ");
                    break;
                case 1:
                    mCircleBounds.offsetTo(mContentBounds.left, mContentBounds.top);
                    Log.d("hhhhhhhhhhhhh", "onDraw: " + mContentBounds.left + "   " + mCircleBounds.width());
                    break;
                case 3:
                    mCircleBounds.offsetTo(mContentBounds.left,
                                            mContentBounds.top + mContentBounds.height() -mCircleBounds.height());
                    /*mCircleBounds.offsetTo(mContentBounds.left + mContentBounds.width() - mCircleBounds.width(),
                                            mContentBounds.top);*/
                    Log.d("hhhhhhhhhhhhh", "onDraw: ");
                    break;
                case 4:
                    mCircleBounds.offsetTo(mContentBounds.left + mContentBounds.width() - mCircleBounds.width(),
                            mContentBounds.top + mContentBounds.height() -mCircleBounds.height());
                    Log.d("hhhhhhhhhhhhh", "onDraw: ");
                    break;

            }

            float cx = mCircleBounds.centerX()-getSize();

            Log.d("HIII", "onDraw: " + getSize());


            float cy = mCircleBounds.centerY();

            /*float cx = mCircleBounds.centerX();
            float cy = mCircleBounds.centerY();*/
            Log.d("YOOOOOOO", "onDraw: " + cx + "  " + cy);


            float radius = mCircleBounds.width() / 2f * mAnimationFactor;
            // Solid circle
            canvas.drawCircle(cx, cy, radius, mCirclePaint);
            // Mask circle
            canvas.drawCircle(cx, cy, radius, mMaskPaint);
            // Count text
            mTextPaint.setTextSize(mTextSize * mAnimationFactor);
            canvas.drawText(mText, cx, cy + mTextHeight / 2f, mTextPaint);
        }
    }

    void changeTextSize(int size){
        TEXT_SIZE_DP = size;
        final float density = getResources().getDisplayMetrics().density;
        mTextSize = TEXT_SIZE_DP * density;
        mTextPaint.setTextSize(mTextSize);
        createCircle();
        setCount(mCount);
    }

    int getCurrentSize(){
        return TEXT_SIZE_DP;
    }

    void setDirection(int direction){
        mDirection = direction;
    }
    int getDirection(){
        return mDirection;
    }
}
