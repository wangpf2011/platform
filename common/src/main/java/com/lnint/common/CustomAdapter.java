package com.lnint.common;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class CustomAdapter extends BaseAdapter {
	private int[] customTo;
    private String[] customFrom;
    private List<? extends Map<String, ?>> customData;
	private int customResource;
	private int customResource1;
	private LayoutInflater customInflater;

	public CustomAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, int resource1, String[] from, int[] to) {
		//super(context, data, resource, from, to);
		this.customData = data;
		this.customFrom = from;
		this.customTo = to;
		this.customResource = resource;
		this.customResource1 = resource1;
		this.customInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
        return customData.size();
    }

	@Override
    public Object getItem(int position) {
        return customData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(position % 2 == 0) {
			return createViewFromResource(position, convertView, parent, customResource);
		}else {
			return createViewFromResource(position, convertView, parent, customResource1);
		}
	}
	
    private View createViewFromResource(int position, View convertView,
            ViewGroup parent, int resource) {
        //View v = customInflater.inflate(resource, parent, false);
    	
        View v;
        if (convertView == null) {
            v = customInflater.inflate(resource, parent, false);
        } else {
            v = convertView;
        }
        
        bindView(position, v);

        return v;
    }
    
    private void bindView(int position, View view) {
        final Map<String, ?> dataSet = customData.get(position);
        if (dataSet == null) {
            return;
        }

        final String[] from = customFrom;
        final int[] to = customTo;
        final int count = to.length;

        for (int i = 0; i < count; i++) {
            final View v = view.findViewById(to[i]);
            if (v != null) {
                final Object data = dataSet.get(from[i]);
                String text = data == null ? "" : data.toString();
                if (text == null) {
                    text = "";
                }
                
                if (v instanceof Checkable) {
                    if (data instanceof Boolean) {
                        ((Checkable) v).setChecked((Boolean) data);
                    } else if (v instanceof TextView) {
                        // Note: keep the instanceof TextView check at the bottom of these
                        // ifs since a lot of views are TextViews (e.g. CheckBoxes).
                        setViewText((TextView) v, text);
                    } else {
                        throw new IllegalStateException(v.getClass().getName() +
                                " should be bound to a Boolean, not a " +
                                (data == null ? "<unknown type>" : data.getClass()));
                    }
                } else if (v instanceof TextView) {
                    // Note: keep the instanceof TextView check at the bottom of these
                    // ifs since a lot of views are TextViews (e.g. CheckBoxes).
                    setViewText((TextView) v, text);
                } else if (v instanceof ImageView) {
                    if (data instanceof Integer) {
                        setViewImage((ImageView) v, (Integer) data);                            
                    } else {
                        setViewImage((ImageView) v, text);
                    }
                } else {
                    throw new IllegalStateException(v.getClass().getName() + " is not a " +
                            " view that can be bounds by this SimpleAdapter");
                }
            }
        }
    }
    
    public void setViewText(TextView v, String text) {
        v.setText(text);
    }
    public void setViewImage(ImageView v, int value) {
        v.setImageResource(value);
    }
    public void setViewImage(ImageView v, String value) {
        try {
            v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            v.setImageURI(Uri.parse(value));
        }
    }
}
