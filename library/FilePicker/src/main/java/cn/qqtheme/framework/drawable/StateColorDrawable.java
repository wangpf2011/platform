package cn.qqtheme.framework.drawable;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;

/**
 * 按下状态与普通状态下显示不同的颜色
 * <br />
 * @author wangpf
 * @since 2017/02/28
 */
public class StateColorDrawable extends StateBaseDrawable {

    public StateColorDrawable(@ColorInt int pressedColor) {
        this(Color.TRANSPARENT, pressedColor);
    }

    public StateColorDrawable(@ColorInt int normalColor, @ColorInt int pressedColor) {
        addState(new ColorDrawable(normalColor), new ColorDrawable(pressedColor));
    }

}
