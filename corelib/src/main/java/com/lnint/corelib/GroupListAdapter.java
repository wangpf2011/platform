package com.lnint.corelib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class GroupListAdapter extends ArrayAdapter<HashMap<String, Object>> {
	private List<String> listTag = null;
    public GroupListAdapter(Context context, List<HashMap<String, Object>> objects, List<String> tags) {
        super(context, 0, objects);
        this.listTag = tags;
    }
     
    @Override
    public boolean isEnabled(int position) {
    	HashMap<String, Object> map = getItem(position);
        if(listTag.contains(map.get("tag"))) {
            return false;
        }
        return super.isEnabled(position);
    }
    
    @SuppressLint("InflateParams")
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        
        HashMap<String, Object> map = getItem(position);
        if(listTag.contains(map.get("tag"))) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item_tag, null);
        }else{
            view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item, null);
            
            TextView textView = (TextView)view.findViewById(R.id.user_center_item);
            textView.setText((String)map.get("name"));
            ImageView imageView = (ImageView)view.findViewById(R.id.user_center_img);
            imageView.setImageResource(Integer.parseInt(String.valueOf(map.get("img"))));
        }
        
        // v.setImageResource(Integer.parseInt(value));
        return view;
    }
}
