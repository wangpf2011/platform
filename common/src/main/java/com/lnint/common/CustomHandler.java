package com.lnint.common;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public class CustomHandler<T> extends Handler {
	//对外部类弱引用
	private WeakReference<T> mOuter;
	
    public CustomHandler(T t) {
	    mOuter = new WeakReference<T>(t);
    }
 
	@Override
	public void handleMessage(Message msg) {
		T outer = mOuter.get();
		if (outer != null) {
			// Do something with outer as your wish.
		}
	}
	
	public WeakReference<T> getReference() {
		return mOuter;
	}
}
