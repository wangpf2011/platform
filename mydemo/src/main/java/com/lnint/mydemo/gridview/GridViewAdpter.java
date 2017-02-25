package com.lnint.mydemo.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnint.mydemo.R;

import java.util.List;
import java.util.Map;

/**
 * Adpter
 * Created by wangpf on 2017/2/13.
 */

public class GridViewAdpter extends BaseAdapter {
    private List<Map<String, String>> items;
    private boolean isChice[];
    private Context context;

    public GridViewAdpter(List<Map<String, String>> items, Context context) {
        this.items = items;
        isChice = new boolean[items.size()];
        for (int i = 0; i < items.size(); i++) {
            isChice[i] = false;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        View view = arg1;
        GridViewAdpter.GetView getView = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.demo_gridview_item, null);
            getView = new GridViewAdpter.GetView();
            getView.relative = (RelativeLayout)view.findViewById(R.id.rl_item);
            getView.imageView = (ImageView)view.findViewById(R.id.image_item);
            getView.text = (TextView)view.findViewById(R.id.txt_filter_name);
            view.setTag(getView);
        } else {
            getView = (GridViewAdpter.GetView) view.getTag();
        }
        //getView.imageView.setImageDrawable(getView(arg0));
        /*getView.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onImageClick(view, arg0);
                }
            }
        });*/
        Map<String, String> map = items.get(arg0);
        getView.text.setText(map.get("name"));
        boolean dis = isChice[arg0];
        if(dis) {
            getView.imageView.setVisibility(View.VISIBLE);
        }else {
            getView.imageView.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    static class GetView {
        RelativeLayout relative;
        ImageView imageView;
        TextView text;
    }

    /**
     * 选中某一项
     * @param posi
     * @return
     */
    public boolean chiceState(int posi) {
        if(isChice[posi]) {
            isChice[posi] = false;
        }else {
            isChice[posi] = true;
        }
        //isChice[post] = isChice[post]==true?false:true;
        this.notifyDataSetChanged();
        return isChice[posi];
    }

    /**
     * 初始化状态
     */
    public void resetState() {
        for(int i=0; i<isChice.length; i++) {
            isChice[i] = false;
        }
        this.notifyDataSetChanged();
    }

    /*private StationFilterAdpter.onImageClickListener clickListener = null;

    public void setOnImageClick(StationFilterAdpter.onImageClickListener listener){
        clickListener = listener;
    }

    public interface onImageClickListener {
        void onImageClick(View v, int position);
    }*/
}
