package com.study.xuan.shapebuilder.shape;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

/**
 * Author : xuan.
 * Data : 2017/9/20.
 * Description :封装GradientDrawable替代用shape.xml，减小apk体积
 */

public class ShapeBuilder{
    private GradientDrawable drawable;

    public ShapeBuilder() {
        drawable = new GradientDrawable();
    }

    public static ShapeBuilder create() {
        return new ShapeBuilder();
    }

    /**
     * 设置shape的type类型
     * @param type RECTANGLE,OVAL,LINE,RING
     */
    public ShapeBuilder Type(int type) {
        drawable.setShape(type);
        return this;
    }

    /**
     * 设置Stroke
     * @param px -width,需要px值
     * @param color -color值
     */
    public ShapeBuilder Stroke(int px, int color) {
        drawable.setStroke(px, color);
        return this;
    }

    /**
     * 边线
     * @param px -width,需要px值
     * @param color -color值
     * @param dashWidth -dashWidth 横线的宽度
     * @param dashGap -dashGap 点与点间的距离
     */
    public ShapeBuilder Stroke(int px, int color, int dashWidth, int dashGap) {
        drawable.setStroke(px, color, dashWidth, dashGap);
        return this;
    }

    /**
     *
     * @param color -背景颜色
     */
    public ShapeBuilder Soild(int color) {
        drawable.setColor(color);
        return this;
    }

    /**
     *
     * @param px -圆角，四个角保持一致
     */
    public ShapeBuilder Radius(float px) {
        drawable.setCornerRadius(px);
        return this;
    }

    /**
     * 圆角
     * @param topleft 左上
     * @param topright 右上
     * @param botleft 左下
     * @param botright 右下
     */
    public ShapeBuilder Radius(float topleft, float topright, float botleft, float botright) {
        drawable.setCornerRadii(new float[]{topleft, topleft, topright, topright, botleft,
                botleft, botright, botright});
        return this;
    }

    /**
     * 渐变，默认的Linear渐变
     * @param startColor 开始颜色
     * @param centerColor 中心颜色
     * @param endColor 结束颜色
     */
    public ShapeBuilder Gradient(int startColor, int centerColor, int endColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setColors(new int[]{startColor, centerColor, endColor});
        }
        return this;
    }

    /**
     * 渐变，设置角度(实质调用的Gradient(GradientDrawable.Orientation orientation, int startColor, int
     centerColor, int endColor)方法)
     * @param angle 角度，需要是45的整数倍
     * @param startColor 开始颜色
     * @param centerColor 中心颜色
     * @param endColor 结束颜色
     */
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

    /**
     * 渐变，设置渐变方向
     * @param orientation 方向支持类型
     *                    0-LEFT_RIGHT
     *                    45-BL_TR
     *                    90-BOTTOM_TOP
     *                    135-BR_TL
     *                    180-RIGHT_LEFT
     *                    225-TR_BL
     *                    270-TOP_BOTTOM
     *                    315-TL_BR
     * @param startColor 开始颜色
     * @param centerColor 中心颜色
     * @param endColor 结束颜色
     */
    public ShapeBuilder Gradient(GradientDrawable.Orientation orientation, int startColor, int
            centerColor, int endColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setOrientation(orientation);
        }
        return Gradient(startColor, centerColor, endColor);
    }

    /**
     * 渐变type
     * @param type linear (default.)-LINEAR_GRADIENT
     *             circular-RADIAL_GRADIENT
     *             sweep-SWEEP_GRADIENT
     * @return
     */
    public ShapeBuilder GradientType(int type) {
        drawable.setGradientType(type);
        return this;
    }

    /**
     *  这两个属性只有在type不为linear情况下起作用。
     * @param x 相对X的渐变位置
     * @param y 相对Y的渐变位置
     * @return
     */
    public ShapeBuilder GradientCenter(float x, float y) {
        drawable.setGradientCenter(x, y);
        return this;
    }

    /**
     * 该属性只有在type="radial"有效
     * @param radius 渐变颜色的半径
     * @return
     */
    public ShapeBuilder GradientRadius(float radius) {
        drawable.setGradientRadius(radius);
        return this;
    }

    /**
     * 设置size
     * @param width 宽
     * @param height 高
     * @return
     */
    public ShapeBuilder setSize(int width, int height) {
        drawable.setSize(width, height);
        return this;
    }

    /**
     * 传入View，设置Bac
     */
    public void build(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        }
    }

    /**
     * 返回构建的drawable
     */
    public GradientDrawable build() {
        return drawable;
    }
}
