package com.yzh.android.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yzh.android.tools.ToolResource;

/**
 * 自定义对话框
 * @author 曾繁添
 * @version 1.0
 */
public  class ProgressDialog extends Dialog {
	
	/**消息TextView*/
	private TextView tvMsg ; 
	public ProgressDialog(Context context, String strMessage) {
		this(context, ToolResource.getStyleId("CustomProgressDialog"),strMessage);
	}
	/**
	 *  @function 进度条的获取
	 * @param context
	 * @param theme
	 * @param strMessage
	 */
	public ProgressDialog(Context context, int theme, String strMessage) {
		super(context, theme);
		this.setContentView(ToolResource.getLayoutId("view_progress_dialog"));
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
	    tvMsg = (TextView) this.findViewById(ToolResource.getIdId("tv_msg"));
	    setMessage(strMessage);
	}
	/**
	 * @function 自定义对话框的设置
	 * @param context 上下文的获取
	 * @param layoutName 布局文件的读取
	 * @param StyleName style的设置
	 * @param flag 找寻数据
	 */
	public ProgressDialog(Context context,String layoutName,String styleName,boolean flag){
		super(context,ToolResource.getStyleId(styleName));
		this.setContentView(ToolResource.getLayoutId(layoutName));
		LayoutInflater inflater =LayoutInflater.from(context);  
        View view =inflater.inflate(ToolResource.getLayoutId(layoutName), null);
        handleDialog(view);
	}
	private void handleDialog(View view) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 设置进度条消息
	 * @param strMessage 消息文本
	 */
	public void setMessage(String strMessage){
		if (tvMsg != null) {
			tvMsg.setText(strMessage);
		}
	}
}
 interface handleInterface{
	public void handleDialog(View view);
}