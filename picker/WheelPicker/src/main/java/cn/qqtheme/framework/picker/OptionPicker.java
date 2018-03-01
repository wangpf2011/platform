package cn.qqtheme.framework.picker;

import android.app.Activity;

import java.util.List;

/**
 * 单项文本选择器
 *
 * @author wangpf
 * @since 2017/02/28
 */
public class OptionPicker extends SinglePicker<String> {

    public OptionPicker(Activity activity, String[] items) {
        super(activity, items);
    }

    public OptionPicker(Activity activity, List<String> items) {
        super(activity, items);
    }

    public void setOnOptionPickListener(OnOptionPickListener listener) {
        super.setOnItemPickListener(listener);
    }

    public static abstract class OnOptionPickListener implements OnItemPickListener<String> {

        public abstract void onOptionPicked(int index, String item);

        @Override
        public final void onItemPicked(int index, String item) {
            onOptionPicked(index, item);
        }

    }

}
