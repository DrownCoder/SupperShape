package com.study.xuan.shapebuilder.shape;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;


/**
 * Author : xuan.
 * Data : 2017/9/20.
 * Description :封装StateListDrawable，配合ShapeBuilder使用效果更佳，替代selector类型xml文件，减小apk体积
 */

public class ShapeListBuilder {
    private StateListDrawable drawable;
    private Drawable mTemplate;

    public ShapeListBuilder(Drawable drawable) {
        this.mTemplate = drawable;
        this.drawable = new StateListDrawable();
    }

    /**
     * @param drawable 传入默认状态下的drawable
     */
    public static ShapeListBuilder create(Drawable drawable) {
        return new ShapeListBuilder(drawable);
    }

    /**
     * 添加状态
     * @param shape 状态对应的shape
     * @param state 状态类型
     *              （这里要注意添加的顺序，只要有一个状态与之相配，背景就会被换掉。
     *              所以不要把大范围放在前面了，会造成没有什么效果了。）
     */
    public ShapeListBuilder addShape(Drawable shape, int... state) {
        drawable.addState(state, shape);
        return this;
    }

    /**
     * 设置背景，记得实现onClick事件监听，修改对应状态，不然无效果
     */
    public void build(View v) {
        addShape(mTemplate);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        }
    }
}
