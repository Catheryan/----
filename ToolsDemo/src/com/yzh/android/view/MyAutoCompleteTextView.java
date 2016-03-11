package com.yzh.android.view;

import com.yzh.android.service.AutoCompleteAdapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;

public class MyAutoCompleteTextView extends RelativeLayout {
	private Context mContext;
	private AutoCompleteTextView tv;
	private TextWatcher textWatcher;

	public MyAutoCompleteTextView(Context context) {
		super(context);
		mContext = context;
	}

	public MyAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public MyAutoCompleteTextView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initViews();
	}

	private void initViews() {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		tv = new AutoCompleteTextView(mContext);
		tv.setLayoutParams(params);
		tv.setPadding(10, 0, 40, 0);

		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		p.addRule(RelativeLayout.CENTER_VERTICAL);
		p.rightMargin = 10;
		this.addView(tv);
	}

	public void setTextWatcher(TextWatcher textWatcher) {
		tv.addTextChangedListener(textWatcher);
	}

	public void setAdapter(AutoCompleteAdapter adapter) {
		tv.setAdapter(adapter);
	}

	public void setThreshold(int threshold) {
		tv.setThreshold(threshold);
	}

	public void setText(String text) {
		tv.setText(text);
	}

	public AutoCompleteTextView getAutoCompleteTextView() {
		return tv;
	}

	public void addTextWatcher(MyTextWatcher myTextWatcher) {
		tv.addTextChangedListener(myTextWatcher);
	}

	abstract class MyTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String str = s.toString();
			String[] temp = getInputAdapter(getInputWorldOrder(str));
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
					android.R.layout.simple_dropdown_item_1line, temp);
			tv.setAdapter(adapter);
		}

		@Override
		public void afterTextChanged(Editable s) {

		}

		protected  abstract Object getInputWorldOrder(String str);

		protected  abstract String[] getInputAdapter(Object inputWorldOrder);
	}
}
