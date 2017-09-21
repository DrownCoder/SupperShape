package com.study.xuan.shapebuilder.shape;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/**
 * Author : xuan.
 * Data : 2017/9/20.
 * Description :input the description of this file.
 */

public class ShapeBuilder implements Cloneable {
    private GradientDrawable drawable;

    public ShapeBuilder() {
        drawable = new GradientDrawable();
    }

    public ShapeBuilder(GradientDrawable drawable) {
        this.drawable = drawable;
    }

    public static ShapeBuilder create() {
        return new ShapeBuilder();
    }

    public ShapeBuilder Type(int type) {
        drawable.setShape(type);
        return this;
    }

    public ShapeBuilder Stroke(int px, int color) {
        drawable.setStroke(px, color);
        return this;
    }

    public ShapeBuilder Stroke(int px, int color, int dashWidth, int dashGap) {
        drawable.setStroke(px, color, dashWidth, dashGap);
        return this;
    }

    public ShapeBuilder Soild(int color) {
        drawable.setColor(color);
        return this;
    }

    public ShapeBuilder Radius(float px) {
        drawable.setCornerRadius(px);
        return this;
    }

    public ShapeBuilder Radius(float topleft, float topright, float botleft, float botright) {
        drawable.setCornerRadii(new float[]{topleft, topleft, topright, topright, botleft,
                botleft, botright, botright});
        return this;
    }

    public ShapeBuilder Gradient(int startColor, int centerColor, int endColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setColors(new int[]{startColor, centerColor, endColor});
        }
        return this;
    }

    public ShapeBuilder Gradient(int angle, int startColor, int centerColor, int endColor) {
        angle = angle % 360;
        GradientDrawable.Orientation orientation = null;
        switch (angle) {
            case 0:
                orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 45:
                orientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 90:
                orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 135:
                orientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 180:
                orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 225:
                orientation = GradientDrawable.Orientation.TR_BL;
                break;
            case 270:
                orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 315:
                orientation = GradientDrawable.Orientation.TL_BR;
                break;
        }
        return Gradient(orientation, startColor, centerColor, endColor);
    }

    public ShapeBuilder Gradient(GradientDrawable.Orientation orientation, int startColor, int
            centerColor, int endColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setOrientation(orientation);
        }
        return Gradient(startColor, centerColor, endColor);
    }

    public ShapeBuilder GradientType(int type) {
        drawable.setGradientType(type);
        return this;
    }

    public ShapeBuilder GradientCenter(float x, float y) {
        drawable.setGradientCenter(x, y);
        return this;
    }

    public ShapeBuilder setSize(int width, int height) {
        drawable.setSize(width, height);
        return this;
    }

    public void build(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        }
    }

    public GradientDrawable getDrawable() {
        return drawable;
    }

    @Override
    public ShapeBuilder clone(){
        return new ShapeBuilder((GradientDrawable) drawable.getConstantState().newDrawable());
    }
}
