package com.wf.bezierview.base;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义View
 * Created by wang on 2019/2/27.
 */
public class MyView extends View {

    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        canvas.drawCircle(150, 150, 100, mPaint); //绘制一个圆，圆心(100, 100), 半径100

        canvas.save();
        canvas.translate(0, 300);  //坐标系向右移动250

        mPaint.setColor(Color.GRAY);
        canvas.drawRect(50, 0, 250, 200, mPaint);  //在新的坐标系中画一个200x200的正方形

        mPaint.setColor(Color.YELLOW);

        Path path = new Path();
        path.moveTo(50, 400);
        path.lineTo(250, 400);
        path.lineTo(50, 600);
        path.close();
        canvas.drawPath(path, mPaint);   //在新的坐标系中画一个三角形
        canvas.restore();
    }
}
