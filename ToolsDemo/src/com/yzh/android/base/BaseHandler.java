package com.yzh.android.base;


import java.lang.ref.WeakReference;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class BaseHandler<T> extends Handler {

	private WeakReference<T> ref;

	/**
	 * ���췽����
	 * @param t Handler���ڵĶ���
	 */
	public BaseHandler(T t) {
		ref = new WeakReference<T>(t);
	}
	
	public BaseHandler(Looper looper,T t){
		super(looper);
		ref = new WeakReference<T>(t);
	}

	@Override
	public void handleMessage(Message msg) {
		if (ref == null) {
			return;
		}
		T t = ref.get();
		if (t == null) {
			return;
		}
		onHandleMessage(msg, t);
	}
	
	public abstract void onHandleMessage(Message msg, T t);
}

