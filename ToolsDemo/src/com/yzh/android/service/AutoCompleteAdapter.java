package com.yzh.android.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.tools.toolsdemo.R;
import com.yzh.android.view.MyAutoCompleteTextView;

public abstract class AutoCompleteAdapter extends BaseAdapter implements Filterable {
	private Context context;
	private ArrayFilter mFilter;
	private ArrayList<String> mOriginalValues;// 所有的Item
	private List<String> mObjects;// 过滤后的item
	private final Object mLock = new Object();
	private int maxMatch = 10;// 最多显示多少个选项,负数表示全部
	private MyAutoCompleteTextView tv;
	public AutoCompleteAdapter(Context context,
			ArrayList<String> mOriginalValues, int maxMatch, MyAutoCompleteTextView tv) {
		this.context = context;
		this.mOriginalValues = mOriginalValues;
		this.maxMatch = maxMatch;
		this.tv=tv;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup parent) {

		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(
					R.layout.simple_list_item_for_autocomplete, null);
			holder.tv = (TextView) convertView
					.findViewById(R.id.simple_item_0);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tv.setText(mObjects.get(position));
		holder.tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/*String obj = mObjects.remove(position);
				mOriginalValues.remove(obj);
				notifyDataSetChanged();*/
				tv.setText(mOriginalValues.get(position));
				tv.clearFocus();
				handleThingAfterChoose(mOriginalValues.get(position), context);
			}
		});
		return convertView;
	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new ArrayFilter();
		}
		return mFilter;
	}
	protected abstract void handleThingAfterChoose(String values,Context context);
	private class ArrayFilter extends Filter {
		FilterResults results = new FilterResults();

		// 根据约束条件调用一个工作线程过滤数据。子类必须实现该方法来执行过滤操作
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			if (constraint == null || constraint.length() == 0) {
				synchronized (mLock) {
					ArrayList<String> list = new ArrayList<String>(
							mOriginalValues);
					results.values = list;
					results.count = list.size();
					return results;
				}
			} else {
				String prefixString = constraint.toString().toLowerCase();
				final int count = mOriginalValues.size();
				final ArrayList<String> newValues = new ArrayList<String>(
						count);
				for (int i = 0; i < count; i++) {
					final String value = mOriginalValues.get(i);
					final String valueText = value.toLowerCase();
					if (valueText.startsWith(prefixString)) {
						newValues.add(value);
					}
					if (maxMatch > 0) {// 有数量限制
						if (newValues.size() > maxMatch - 1) {// 不要太多
							break;
						}
					}
				}
				results.values = newValues;
				results.count = newValues.size();
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			mObjects = (List<String>) results.values;
			if (results.count > 0) {
				notifyDataSetChanged();
			} else {
				notifyDataSetInvalidated();
			}
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mObjects.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mObjects.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}

class ViewHolder {
	TextView tv;
}
