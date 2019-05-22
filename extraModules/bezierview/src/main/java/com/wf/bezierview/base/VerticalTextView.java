package com.wf.bezierview.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
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
public class VerticalTextView extends LinearLayout {
    private TextView mTitle;
    private ImageView mImageView;
    private LayoutParams imageLayoutParams;
    private LayoutParams textLayoutParams;

    public VerticalTextView(Context context) {
        super(context);
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VerticalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setOrientation(LinearLayout.VERTICAL);
        try{
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.VerticalTextView);

            mImageView = new ImageView(context);
            imageLayoutParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            float imageWidth = typedArray.getDimension(R.styleable.VerticalTextView_vimageWidth, DensityUtil.dip2px(context,35));
            imageLayoutParams.width = (int)imageWidth;
            float imageHeight = typedArray.getDimension(R.styleable.VerticalTextView_vimageHeight, DensityUtil.dip2px(context,35));
            imageLayoutParams.height = (int)imageHeight;
            mImageView.setLayoutParams(imageLayoutParams);
            mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Drawable imageSrc = typedArray.getDrawable(R.styleable.VerticalTextView_vimageSrc);
            mImageView.setImageDrawable(imageSrc);
            addView(mImageView, imageLayoutParams);

            mTitle = new TextView(context);
            int textColor = typedArray.getColor(R.styleable.VerticalTextView_vtextColor,
                    0XFFFFFFFF);
            String textStr = typedArray.getString(R.styleable.VerticalTextView_vtextStr);
            int textSize = typedArray.getInteger(R.styleable.VerticalTextView_vtextSize1, 16);
            imageLayoutParams.width = (int)imageWidth;
            mTitle.setText(textStr);
            mTitle.setTextColor(textColor);
            mTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            mTitle.setGravity(Gravity.CENTER);
            textLayoutParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            float textMarginTop = typedArray.getDimension(R.styleable.VerticalTextView_vtextMarginTop, DensityUtil.dip2px(context,10));
            float textMarginBottom = typedArray.getDimension(R.styleable.VerticalTextView_vtextMarginBottom, DensityUtil.dip2px(context,10));
            textLayoutParams.setMargins(0, (int)textMarginTop, 0, (int)textMarginBottom);
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
