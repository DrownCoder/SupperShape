package com.study.xuan.easywidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;


import com.study.xuan.shapebuilder.shape.ShapeBuilder;

import static android.graphics.drawable.GradientDrawable.RECTANGLE;

/**
 * Author : xuan.
 * Date : 2017/12/23.
 * Description :方便使用的TextView,目前支持:
 * 1.圆角和边线颜色和宽度,soild
 * 2.iconFont配合textLeft,textRight,textPadding,iconColor
 */

public class EasyTextView extends TextView {
    private static final String EMPTY_SPACE = "\u3000";
    private Context mContext;
    private float mRadius;
    private float mRadiusTopLeft, mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight;
    private int mStrokeColor;
    private int mStrokeWidth;
    private int mSoild;
    private float mTextPadding;
    private String mTextLeft;
    private String mTextRight;
    private int mIconColor;
    private String iconString;
    //icon的index
    private int iconIndex = 0;

    public EasyTextView(Context context) {
        this(context, null);
    }

    public EasyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttr(context, attrs);
        init();
    }

    private void init() {
        initIconFont();
        initShape();
    }

    private void initIconFont() {
        try {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "iconfont.ttf"));
        } catch (Exception e) {

        }
        iconString = getText().toString();
        if (!TextUtils.isEmpty(mTextLeft) || !TextUtils.isEmpty(mTextRight)) {
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getText());
            if (!TextUtils.isEmpty(mTextLeft)) {
                if (mTextPadding != 0) {
                    stringBuilder.insert(0, EMPTY_SPACE);
                    iconIndex++;
                }
                stringBuilder.insert(0, mTextLeft);
                iconIndex += mTextLeft.length();
            }

            if (!TextUtils.isEmpty(mTextRight)) {
                if (mTextPadding != 0) {
                    stringBuilder.append(EMPTY_SPACE);
                }
                stringBuilder.append(mTextRight);
            }
            if (mTextPadding != 0) {
                if (!TextUtils.isEmpty(mTextLeft)) {
                    AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan((int) mTextPadding);
                    stringBuilder.setSpan(sizeSpan, iconIndex - 1, iconIndex, Spanned
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                if (!TextUtils.isEmpty(mTextRight)) {
                    AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan((int) mTextPadding);
                    stringBuilder.setSpan(sizeSpan, iconIndex + 1, iconIndex + 2, Spanned
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
                }

            }
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(mIconColor);
            stringBuilder.setSpan(foregroundColorSpan, iconIndex, iconIndex + 1, Spanned
                    .SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(stringBuilder);
        }
    }

    private void initShape() {
        if (mRadius == -0 && mRadiusTopLeft == 0 && mRadiusTopRight == 0 && mRadiusBottomLeft == 0
                && mRadiusBottomRight == 0 && mStrokeColor == -1 && mStrokeWidth == 0 && mSoild ==
                -1) {
            return;
        } else {
            setShape();
        }
    }

    private void setShape() {
        if (mRadius != 0) {
            ShapeBuilder.create().Type(RECTANGLE).Radius(mRadius).Soild(mSoild).Stroke
                    (mStrokeWidth, mStrokeColor).build(this);
        } else {
            ShapeBuilder.create().Type(RECTANGLE).Radius(mRadiusTopLeft,
                    mRadiusTopRight, mRadiusBottomLeft, mRadiusBottomRight).Soild(mSoild).Stroke
                    (mStrokeWidth, mStrokeColor).build(this);
        }
    }

    private void clearText() {
        setText(iconString);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.EasyTextView);
        mRadius = array.getDimensionPixelOffset(R.styleable.EasyTextView_totalRadius, 0);
        mRadiusTopLeft = array.getDimensionPixelSize(R.styleable.EasyTextView_radiusTopLeft, 0);
        mRadiusTopRight = array.getDimensionPixelSize(R.styleable.EasyTextView_radiusTopRight, 0);
        mRadiusBottomLeft = array.getDimensionPixelSize(R.styleable.EasyTextView_radiusBottomLeft,
                0);
        mRadiusBottomRight = array.getDimensionPixelSize(R.styleable
                .EasyTextView_radiusBottomRight, 0);
        mStrokeColor = array.getColor(R.styleable.EasyTextView_strokeColor, -1);
        mStrokeWidth = array.getDimensionPixelOffset(R.styleable.EasyTextView_strokeWidth, 0);
        mSoild = array.getColor(R.styleable.EasyTextView_soildBac, -1);
        mTextPadding = array.getDimensionPixelOffset(R.styleable.EasyTextView_textPadding, 0);
        mTextLeft = array.getString(R.styleable.EasyTextView_textLeft);
        mTextRight = array.getString(R.styleable.EasyTextView_textRight);
        mIconColor = array.getColor(R.styleable.EasyTextView_iconColor, -1);
        array.recycle();
    }

    /**
     * 设置shape背景颜色
     */
    public void setBackgroundSold(int soild) {
        this.mSoild = soild;
        setShape();
    }

    /**
     * 设置icon颜色
     */
    public EasyTextView setIconColor(int color) {
        this.mIconColor = color;
        clearText();
        initIconFont();
        return this;
    }

    /**
     * 设置左文案
     */
    public EasyTextView setTextLeft(String textLeft) {
        this.mTextLeft = textLeft;
        initIconFont();
        return this;
    }

    /**
     * 设置右文案
     */
    public EasyTextView setTextRight(String textRight) {
        this.mTextRight = textRight;
        initIconFont();
        return this;
    }
}
