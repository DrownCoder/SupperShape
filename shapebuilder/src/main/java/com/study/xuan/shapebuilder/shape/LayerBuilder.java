package com.study.xuan.shapebuilder.shape;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;

/**
 * Author : xuan.
 * Date : 2017/9/28.
 * Description :input the description of this file.
 */

public class LayerBuilder {
    private LayerDrawable drawable;

    public LayerBuilder(Drawable[] drawables) {
        drawable = new LayerDrawable(drawables);
    }

    public static LayerBuilder create(Drawable... drawables) {
        return new LayerBuilder(drawables);
    }

    public LayerBuilder Left(int index, int px) {
        drawable.setLayerInset(index, 0, 0, 0, px);
        return this;
    }

    public LayerBuilder Top(int index, int px) {
        drawable.setLayerInset(index, 0, 0, 0, px);
        return this;
    }

    public LayerBuilder Right(int index, int px) {
        drawable.setLayerInset(index, 0, 0, 0, px);
        return this;
    }

    public LayerBuilder Bottom(int index, int px) {
        drawable.setLayerInset(index, 0, 0, 0, px);
        return this;
    }

    public LayerBuilder setInset(int index,int left,int top,int right,int bottom) {
        drawable.setLayerInset(index, left, top, right, bottom);
        return this;
    }

    public void build(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        }else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public Drawable build() {
        return drawable;
    }
}
