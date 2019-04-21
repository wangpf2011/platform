package com.wf.core.widget.spinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wf.core.R;

import java.util.ArrayList;
import java.util.List;

public class NormalSpinerAdapter extends BaseAdapter {

	public static interface IOnItemSelectListener{
		public void onItemClick(int pos);
	};
	
	 private List<String> mObjects = new ArrayList<String>();
	    
	 private LayoutInflater mInflater;
	
	 public  NormalSpinerAdapter(Context context) {
		 init(context);
	 }
	 
	 public void refreshData(List<String> objects, int selIndex) {
		 mObjects = objects;
		 if(selIndex < 0) {
			 selIndex = 0;
		 }
		 if(selIndex >= mObjects.size()) {
			 selIndex = mObjects.size() - 1;
		 }
	 }
	 
	 private void init(Context context) {
	        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 }
	    
	@Override
	public int getCount() {
		return mObjects.size();
	}

	@Override
	public Object getItem(int pos) {
		return mObjects.get(pos).toString();
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int pos, View convertView, ViewGroup arg2) {
		 ViewHolder viewHolder;
    	 
	     if(convertView == null) {
	    	 convertView = mInflater.inflate(R.layout.spiner_listview_item, null);
	         viewHolder = new ViewHolder();
	         viewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
	         convertView.setTag(viewHolder);
	     }else {
	         viewHolder = (ViewHolder) convertView.getTag();
	     }

	     
	     String item = (String) getItem(pos);
		 viewHolder.mTextView.setText(item);

	     return convertView;
	}

	public static class ViewHolder {
	    public TextView mTextView;
    }
}
