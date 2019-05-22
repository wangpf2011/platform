package com.wf.bezierview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二次贝塞尔曲线
 * Created by wang on 2019/2/26.
 */

public class BezierQuadView extends View {
    private Point controlPoint = new Point(200, 200);

    public BezierQuadView(Context context) {
        super(context);
    }

    public BezierQuadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierQuadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        Path path = new Path();
        path.moveTo(100, 500);
        path.quadTo(controlPoint.x, controlPoint.y, 700, 500);

        //绘制路径
        canvas.drawPath(path, paint);
        //绘制辅助点
        canvas.drawPoint(controlPoint.x,controlPoint.y,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                controlPoint.x = (int) event.getX();
                controlPoint.y = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
