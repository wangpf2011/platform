package com.wf.core.widget.customcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomCircleView extends View {
    protected float mRadius;
    protected float mCenterX;
    protected float mCenterY;

    private Paint mPaint;
    private int mColor;
    private int mColor1;

    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mColor = Color.parseColor("#ffffff");
        mColor1 = Color.parseColor("#4596C3");
        mPaint.setColor(mColor); // Set default
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCircleView(Context context) {
        super(context);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (width > height)
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        else
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
        mRadius = getWidth() / 2 - 10;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRadius = w / 2 - 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        mPaint.setColor(mColor1);
        canvas.drawCircle(mCenterX, mCenterY, mRadius-1, mPaint);
    }
}