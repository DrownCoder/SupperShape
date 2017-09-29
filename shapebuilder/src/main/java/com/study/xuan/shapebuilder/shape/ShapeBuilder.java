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
    private AttrContainer container;
    private boolean isOperate;
    public ShapeBuilder() {
        drawable = new GradientDrawable();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            container = new AttrContainer();
        }
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
        if (container != null) {
            container.type = type;
        }
        return this;
    }

    /**
     * 设置Stroke
     * @param px -width,需要px值
     * @param color -color值
     */
    public ShapeBuilder Stroke(int px, int color) {
        drawable.setStroke(px, color);
        if (container != null) {
            container.stokewidth = px;
            container.stokeColor = color;
        }
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
        if (container != null) {
            container.stokewidth = px;
            container.stokeColor = color;
            container.dashWidth = dashWidth;
            container.dashGap = dashGap;
        }
        return this;
    }

    /**
     *
     * @param color -背景颜色
     */
    public ShapeBuilder Soild(int color) {
        drawable.setColor(color);
        if (container != null) {
            container.soild = color;
        }
        return this;
    }

    /**
     *
     * @param px -圆角，四个角保持一致
     */
    public ShapeBuilder Radius(float px) {
        drawable.setCornerRadius(px);
        if (container != null) {
            container.setRadius(px, px, px, px);
        }
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
        if (container != null) {
            container.setRadius(topleft, topright, botleft, botright);
        }
        return this;
    }

    /**
     * 渐变，默认的Linear渐变
     * @param startColor 开始颜色
     * @param centerColor 中心颜色
     * @param endColor 结束颜色
     */
    public ShapeBuilder Gradient(int startColor, int centerColor, int endColor) {
        return GradientInit(GradientDrawable.Orientation.TOP_BOTTOM, startColor, centerColor,
                endColor);
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
        return GradientInit(orientation, startColor, centerColor, endColor);
    }

    /**
     * 兼容低版本，重新构造drawable，对应调用operateMethod方法重新build，
     * 保证新的drawable与原始drawabel相同
     */
    private ShapeBuilder GradientInit(GradientDrawable.Orientation orientation, int startColor, int
            centerColor, int endColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setOrientation(orientation);
            drawable.setColors(new int[]{startColor, centerColor, endColor});
        } else {
            isOperate = true;
            drawable = new GradientDrawable(orientation, new int[]{startColor, centerColor,
                    endColor});
        }
        return this;
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
        if (container != null) {
            container.gradientType = type;
        }
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
        if (container != null) {
            container.gradientCenterX = x;
            container.gradientCenterY = y;
        }
        return this;
    }

    /**
     * 该属性只有在type="radial"有效
     * @param radius 渐变颜色的半径
     * @return
     */
    public ShapeBuilder GradientRadius(float radius) {
        drawable.setGradientRadius(radius);
        if (container != null) {
            container.gradinetRadius = radius;
        }
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
        if (container != null) {
            container.width = width;
            container.height = height;
        }
        return this;
    }

    /**
     * 传入View，设置Bac
     */
    public void build(View v) {
        build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(drawable);
        } else {
            v.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 返回构建的drawable
     */
    public GradientDrawable build() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return drawable;
        } else {
            if (isOperate) {
                operateMethod();
            }
        }
        return drawable;
    }

    /**
     * Build.VERSION_CODES.JELLY_BEAN（4.1）以下渐变需要重新构造drawable，所以需要重新build
     */
    private void operateMethod() {
        if (container != null) {
            this.Type(container.type)
                .Stroke(container.stokewidth, container.stokeColor, container.dashWidth,
                            container.dashGap)
                .Radius(container.topLeft,container.topRight,container.botLeft,container.botRight)
                .setSize(container.width,container.height)
                .GradientType(container.gradientType)
                .GradientCenter(container.gradientCenterX,container.gradientCenterY)
                .GradientRadius(container.gradinetRadius);
            if (container.soild != 0) {
                Soild(container.soild);
            }
        }
    }

    private class AttrContainer {
        public int type;
        public int stokewidth;
        public int stokeColor;
        public int dashWidth;
        public int dashGap;
        public int soild;
        public float topLeft, topRight, botLeft, botRight;
        public int width, height;
        public int gradientType;
        public float gradinetRadius;

        public float gradientCenterX, gradientCenterY;

        private void setRadius(float topleft, float topright, float botleft, float botright) {
            this.topLeft = topleft;
            this.topRight = topright;
            this.botLeft = botleft;
            this.botRight = botright;
        }
    }
}
