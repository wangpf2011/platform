package com.wf.bezierview.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wf.bezierview.R;
import com.wf.bezierview.utils.DensityUtil;

/**
 * 基于LinearLayout自定义ImageView+TextView
 *
 * Created by wangpf
 * Created at 2018/9/17 15:20
 */
public class HorizontalTextView extends LinearLayout {
    private TextView mTitle;
    private ImageView mImageView;
    private LayoutParams imageLayoutParams;
    private LayoutParams textLayoutParams;

    public HorizontalTextView(Context context) {
        super(context, null);
    }

    public HorizontalTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context, attrs);
    }

    public HorizontalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setOrientation(LinearLayout.HORIZONTAL);
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalTextView);

            mImageView = new ImageView(context);
            imageLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            float imageWidth = typedArray.getDimension(R.styleable.HorizontalTextView_himageWidth, DensityUtil.dip2px(context,30));
            imageLayoutParams.width = (int)imageWidth;
            float imageHeight = typedArray.getDimension(R.styleable.HorizontalTextView_himageHeight, DensityUtil.dip2px(context,30));
            imageLayoutParams.height = (int)imageHeight;
            mImageView.setLayoutParams(imageLayoutParams);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Drawable imageSrc = typedArray.getDrawable(R.styleable.HorizontalTextView_himageSrc);
            mImageView.setImageDrawable(imageSrc);
            addView(mImageView, imageLayoutParams);

            mTitle = new TextView(context);
            int textColor = typedArray.getColor(R.styleable.HorizontalTextView_htextColor,
                    0XFFFFFFFF);
            String textStr = typedArray.getString(R.styleable.HorizontalTextView_htextStr);
            mTitle.setText(textStr);
            mTitle.setTextColor(textColor);
            mTitle.setGravity(Gravity.CENTER);
            textLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            float textMarginLeft = typedArray.getDimension(R.styleable.HorizontalTextView_htextMarginLeft, DensityUtil.dip2px(context,10));
            textLayoutParams.setMargins((int)textMarginLeft, 0, 0, 0);
            addView(mTitle, textLayoutParams);
            typedArray.recycle();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setmTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
