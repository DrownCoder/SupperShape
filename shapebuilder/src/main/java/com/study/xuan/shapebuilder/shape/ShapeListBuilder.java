package com.study.xuan.shapebuilder.shape;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;


/**
 * Author : xuan.
 * Data : 2017/9/20.
 * Description :input the description of this file.
 */

public class ShapeListBuilder {
    private StateListDrawable drawable;

    public ShapeListBuilder() {
        drawable = new StateListDrawable();
    }

    public static ShapeListBuilder create() {
        return new ShapeListBuilder();
    }

    public ShapeListBuilder addShape(Drawable shape, int... state) {
        drawable.addState(state, shape);
        return this;
    }

    public ShapeListBuilder setTemplate(ShapeBuilder builder) {
        addShape(builder.getDrawable());
        return this;
    }

    public void build(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        }
    }
}
