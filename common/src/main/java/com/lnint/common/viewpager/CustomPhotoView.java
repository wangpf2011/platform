package com.lnint.common.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import uk.co.senab.photoview.PhotoView;

public class CustomPhotoView extends PhotoView {

	public CustomPhotoView(Context context) {
		super(context);
	}
	
	/**
     * 初始化ImageView图片
     * @param bitmap
     */
    public void setImage(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }
}
