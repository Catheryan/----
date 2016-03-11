package com.yzh.android.view;

import com.tools.toolsdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * 自定义RadioButton,增加属性key/value
 * @author 曾繁添
 * @version 1.0
 *
 */
public class RadioButton extends android.widget.RadioButton {

	private int leftHeight = -1;
	private int leftWidth = -1;
	private int rightHeight = -1;
	private int rightWidth = -1;
	private int topHeight = -1;
	private int topWidth = -1;
	private int bottomHeight = -1;
	private int bottomWidth = -1;
	private String mKey;
	private String mValue;

	public RadioButton(Context context) {
		this(context,null);
	}
	
	public RadioButton(Context context, AttributeSet attrs) {
		this(context, attrs,android.R.attr.radioButtonStyle);
	}

	public RadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
		//获取自定义属性和默认值
//		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.TextView);
//		mKey = mTypedArray.getString(R.styleable.TextView_key);
//		mValue = mTypedArray.getString(R.styleable.TextView_value);
//		mTypedArray.recycle();
	}
	private void init(Context context, AttributeSet attrs, int defStyle) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.TabTextView, defStyle, 0);
		if (array != null) {
			int count = array.getIndexCount();
			int index = 0;
			for (int i = 0; i < count; i++) {
				index = array.getIndex(i);
				switch (index) {
				case R.styleable.TabTextView_bottom_height:
					bottomHeight = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_bottom_width:
					bottomWidth = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_left_height:
					leftHeight = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_left_width:
					leftWidth = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_right_height:
					rightHeight = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_right_width2:
					rightWidth = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_top_height:
					topHeight = array.getDimensionPixelSize(index, -1);
					break;
				case R.styleable.TabTextView_top_width:
					topWidth = array.getDimensionPixelSize(index, -1);
					break;
				}
			}
			Drawable[] drawables = getCompoundDrawables();
			int dir = 0;
			// 0-left; 1-top; 2-right; 3-bottom;
			for (Drawable drawable : drawables) {
				// 设定图片大小
				setImageSize(drawable, dir++);
			}
			setCompoundDrawables(drawables[0], drawables[1], drawables[2],
					drawables[3]);
		}
		array.recycle();
	}

	private void setImageSize(Drawable d, int dir) {
		if (d == null) {
			return;
		}

		int height = -1;
		int width = -1;
		// 根据方向给宽和高赋值
		switch (dir) {
		case 0:
			// left
			height = leftHeight;
			width = leftWidth;
			break;
		case 1:
			// top
			height = topHeight;
			width = topWidth;
			break;
		case 2:
			// right
			height = rightHeight;
			width = rightWidth;
			break;
		case 3:
			// bottom
			height = bottomHeight;
			width = bottomWidth;
			break;
		}
		// 如果有某个方向的宽或者高没有设定值，则不去设定图片大小
		if (width != -1 && height != -1) {
			d.setBounds(0, 0, width, height);
		}
	}
	public String getKey(){
		return mKey;
	}
	
	public void setKey(String key){
		this.mKey = key;
	}
	
	public String getValue() {
		return mValue;
	}

	public void setValue(String value) {
		this.mValue = value;
	}
}
