package com.study.xuan.shapebuilder.shape;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

/**
 * Author : xuan.
 * Date : 2018/1/27.
 * Description :interface of shapebuilder
 */

public interface IShape {
    public ShapeBuilder Type(int type);

    public ShapeBuilder Stroke(int px, int color);

    public ShapeBuilder Stroke(int px, int color, int dashWidth, int dashGap);

    public ShapeBuilder Solid(int color);

    public ShapeBuilder Radius(float px);

    public ShapeBuilder Radius(float topleft, float topright, float botleft, float botright);

    public ShapeBuilder Gradient(int startColor, int centerColor, int endColor);

    public ShapeBuilder Gradient(int angle, int startColor, int centerColor, int endColor);

    public ShapeBuilder Gradient(GradientDrawable.Orientation orientation, int startColor, int
            centerColor, int endColor);

    public ShapeBuilder GradientType(int type);

    public ShapeBuilder GradientCenter(float x, float y);

    public ShapeBuilder GradientRadius(float radius);

    public ShapeBuilder setSize(int width, int height);

    public void build(View v);

    public GradientDrawable build();
}
