package com.lnint.corelib.widget.progressdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnint.corelib.R;

/**
 * <P>自定义弹出式进度框</P>
 *
 * eg：http请求时弹出进度框
 *
 * @version 1.0
 * @author 王朋飞 2015年6月17日 下午5:14:07
 * @since JDK 1.6
 */
public class CustomProgressDialog extends Dialog {
	@SuppressWarnings("unused")
	private Context context = null;
	private static CustomProgressDialog customProgressDialog = null;
	
	public CustomProgressDialog(Context context) {
		super(context);
		this.context = context;
	}
	
	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}
	
	public static CustomProgressDialog createDialog(Context context) {
		customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.custom_progress_dialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
	
		return customProgressDialog;
	}
	
	public void onWindowFocusChanged(boolean hasFocus) {
		if (customProgressDialog == null) {
			return;
		}
	
		ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
		animationDrawable.start();
	}
	
	/**
	 * 
	 * [Summary] setTitile
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public CustomProgressDialog setTitile(String strTitle) {
		return customProgressDialog;
	}
	
	/**
	 * 
	 * [Summary] setMessage
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public CustomProgressDialog setMessage(String strMessage) {
		TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loadingmsg);
	
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	
		return customProgressDialog;
	}
}