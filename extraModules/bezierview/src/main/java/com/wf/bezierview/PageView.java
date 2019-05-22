package com.wf.bezierview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region.Op;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

/**
 * 贝塞尔曲线实现翻页效果
 *
 * Created by wangpf
 * Created at 2018/9/17 17:19
 */
public class PageView extends View implements OnTouchListener{
	
	private static final String TAG = "com.wf.view.bezier.PageView";
	
	private static final int offset = 400;
		
	private Paint paint;	
	private Path path;
	private Path pathNext;
	
//	private Bitmap mBitmapFront, mBitmapBack;	
	
	private Context mContext;
	
	private int mWidth,mHeight;
	
	private float ax,ay,bx,by,cx,cy,dx,dy,ex,ey,fx,fy,gx,gy,hx,hy,ix,iy,jx,jy,kx,ky;
	
	private float cornerX, cornerY;
	
	private float outX,outY;
	
	private ValueAnimator valueAnimator;
		
	public PageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public PageView(Context context){
		super(context);
		init(context);
	}
	
	private void init(Context context){
		mContext = context;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(1);
		path = new Path();
		pathNext = new Path();		
		setOnTouchListener(this);
//		mBitmapFront = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.photo1);
//		mBitmapBack =  BitmapFactory.decodeResource(mContext.getResources(), R.drawable.photo2);
			
		valueAnimator = ValueAnimator.ofFloat(0,50f);
		valueAnimator.addUpdateListener(mAnimatorUpdateListener);
	}
	
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();
		ax = cornerX = mWidth;
		ay = cornerY = mHeight;
		
	}

	public void onDraw(Canvas canvas){				
		calculatePoints();
		canvas.save();			
		path.reset();
		path.moveTo(jx, jy);
		path.quadTo(hx, hy, kx, ky);
		path.lineTo(ax, ay);
		path.lineTo(bx, by);
		path.quadTo(ex, ey, cx, cy);
		path.lineTo(fx, fy);
		path.close();						
		paint.setColor(Color.GREEN);
		canvas.drawPath(path, paint);
		canvas.restore();
		
		canvas.save();
		canvas.clipRect(canvas.getClipBounds());
		canvas.clipPath(path,Op.XOR);
//		canvas.drawBitmap(mBitmapFront,0,0,null);		
		canvas.drawColor(Color.BLUE);
		canvas.restore();
		
		canvas.save();	
		canvas.clipPath(path);
		pathNext.reset();
		pathNext.moveTo(cx, cy);
		pathNext.quadTo(dx, dy, dx, dy);
		pathNext.lineTo(ix, iy);
		pathNext.quadTo(jx, jy, jx, jy);
		pathNext.lineTo(fx, fy);
		pathNext.close();
		canvas.clipPath(pathNext, Op.DIFFERENCE);
		canvas.drawColor(Color.YELLOW);
		canvas.restore();	
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			float x = event.getX();
			float y = event.getY();
			if (x < mWidth / 2 && y < mHeight / 2) {
				//second
				cornerX = 0;
				cornerY = 0;
				outX = mWidth + offset;
				outY = mHeight + offset;
			}else if(x < mWidth / 2 && y > mHeight / 2){
				//third
				cornerX = 0;
				cornerY = mHeight;
				outX = mWidth + offset;
				outY = -offset;
			}else if(x > mWidth / 2 && y < mHeight / 2){
				//first
				cornerX = mWidth;
				cornerY = 0;
				outX = -offset;
				outY = mHeight + offset;
			}else if(x > mWidth / 2 && y > mHeight / 2){
				//fourth
				cornerX = mWidth;
				cornerY = mHeight;
				outX = -offset;
				outY = -offset;
			}
			ax = x;
			ax = y;
			break;
		case MotionEvent.ACTION_MOVE:		
			ax = event.getX();
			ay= event.getY();			
			postInvalidate();
			break;
		case MotionEvent.ACTION_UP:
			valueAnimator.setDuration(500).start();			
			break;
		}
		return true;
	}	
	
	private void calculatePoints(){					
		fx = cornerX;
		fy = cornerY;
		
		gx = (ax + fx) / 2;
		gy = (ay + fy) / 2;
		
		float gm = fy - gy;
		float mf = fx - gx;
		float em = gm * gm / mf;
		
		ex = gx - em;
		ey = fy;
			
		float hm = mf * mf / gm;
		
		hx = fx;
		hy = gy - hm;
		
		
		cx = ex - (fx - ex)/2;
		cy = fy;
		
		jx = fx;
		jy = hy - (fy - hy)/2;
		
		bx = (ax + ex) / 2;
		by = (ay + ey) / 2;
		
		kx = (ax + hx) / 2;
		ky = (ay + hy) / 2;
		
		//p middle point of the bc;
		float px = (bx + cx) / 2;
		float py = (by + cy) / 2;
		
		dx = (px + ex) / 2;
		dy = (py + ey) / 2;
			
		
		px = (kx + jx) / 2;
		py = (ky + jy) / 2;
		
		ix = (px + hx) / 2;
		iy = (py + hy) / 2;		
	}
	
	private AnimatorUpdateListener mAnimatorUpdateListener = new AnimatorUpdateListener() {
		
		@Override
		public void onAnimationUpdate(ValueAnimator animation) {			
			float currentValue = (Float)animation.getAnimatedValue();
			float fraction = currentValue / 50f; 
			ax += fraction * (outX - ax);
			ay += fraction * (outY - ay);
			postInvalidate();
		}
	};
}
