package cn.qqtheme.framework.http;

import android.widget.AbsListView;
import android.widget.ImageView;

/**
 * 图片加载引擎接口
 * <br />
 * @author wangpf
 * @since 2017/02/28
 */
public interface ImageLoadEngine {

    void display(String urlOrPath, ImageView view);

    void display(String urlOrPath, ImageView view, int width, int height);

    void onScrollFling(AbsListView view);

    void onScrollFinish(AbsListView view);

}
