package com.wf.andos.common.roundedimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.lidroid.xutils.bitmap.core.AsyncDrawable;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <P>ImageView扩展</P>
 *
 * eg：展示圆形的用户头像
 * 
 * @version 1.0
 * @author 王朋飞 2015年6月17日 下午5:14:07 
 * @since JDK 1.6
 */
public class RoundedImageView extends AppCompatImageView {
	public RoundedImageView(Context context) {
	  super(context);
	}

	public RoundedImageView(Context context, AttributeSet attrs) {
	  super(context, attrs);
	}

	public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
	  super(context, attrs, defStyle);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {

	  Drawable drawable = getDrawable();

	  if (drawable == null) {
	    return;
	  }

	  if (getWidth() == 0 || getHeight() == 0) {
	    return; 
	  }
	  Bitmap b = null;
	  if(drawable instanceof BitmapDrawable){
	    b =  ((BitmapDrawable)drawable).getBitmap() ;
	  }else if(drawable instanceof AsyncDrawable) {
	    b = Bitmap 
	        .createBitmap( 
	        drawable.getIntrinsicWidth(), 
	        drawable.getIntrinsicHeight(), 
	        drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
	        : Config.RGB_565);
	        Canvas canvas1 = new Canvas(b); 
	        // canvas.setBitmap(bitmap); 
	        //drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()); 
	        drawable.draw(canvas1); 
	  }
	  Bitmap bitmap = b.copy(Config.ARGB_8888, true);

	  int w = getWidth(), h = getHeight();


	  Bitmap roundBitmap =  getCroppedBitmap(bitmap, w ,h);
	  canvas.drawBitmap(roundBitmap, 0,0, null);

	}

	/**
	 * 图片圆角
	 * @param bmp
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap getCroppedBitmap(Bitmap bmp, int w, int h) {
	  Bitmap sbmp = Bitmap.createScaledBitmap(bmp, w, h, false);
	  
	  Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
	      sbmp.getHeight(), Config.ARGB_8888);
	  Canvas canvas = new Canvas(output);

	  //final int color = 0xffa19774;
	  final Paint paint = new Paint();
	  final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
	  final RectF rectF = new RectF(rect);  

	  paint.setAntiAlias(true);
	  paint.setFilterBitmap(true);
	  paint.setDither(true);
	  canvas.drawARGB(0, 0, 0, 0);
	  paint.setColor(Color.parseColor("#BAB399"));
	  //canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,sbmp.getWidth() / 2+0.1f, paint);//画圆
	  canvas.drawRoundRect(rectF, 10, 10, paint);//画圆角
	  paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	  canvas.drawBitmap(sbmp, rect, rect, paint);
	  
      return output;
	}
	
	/**
	 * 圆形图片
	 * @param bmp
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap getCircleBitmap(Bitmap bmp, int w, int h) {
	  Bitmap sbmp = Bitmap.createScaledBitmap(bmp, w, h, false);
	  
	  Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
	      sbmp.getHeight(), Config.ARGB_8888);
	  Canvas canvas = new Canvas(output);

	  final Paint paint = new Paint();
	  final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

	  paint.setAntiAlias(true);
	  paint.setFilterBitmap(true);
	  paint.setDither(true);
	  canvas.drawARGB(0, 0, 0, 0);
	  paint.setColor(Color.parseColor("#BAB399"));
	  canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,sbmp.getWidth() / 2+0.1f, paint);//画圆
	  paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	  canvas.drawBitmap(sbmp, rect, rect, paint);
	  
      return output;
	}
	
	/**
     * 获取网落图片资源
     * @param url
     * @return
     */
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
         
        return bitmap;
    }
}
