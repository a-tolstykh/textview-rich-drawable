package com.tolstykh.textviewrichdrawable.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tolstykh.library.R;
import com.tolstykh.textviewrichdrawable.DrawableEnriched;

public class RichDrawableHelper implements DrawableEnriched {

    private static final int LEFT_DRAWABLE_INDEX = 0;
    private static final int TOP_DRAWABLE_INDEX = 1;
    private static final int RIGHT_DRAWABLE_INDEX = 2;
    private static final int BOTTOM_DRAWABLE_INDEX = 3;

    private Context mContext;

    private int mDrawableWidth;
    private int mDrawableHeight;
    private int mDrawableStartVectorId;
    private int mDrawableTopVectorId;
    private int mDrawableEndVectorId;
    private int mDrawableBottomVectorId;

    @ColorInt
    private int mDrawableTint;

    public RichDrawableHelper(@NonNull Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mContext = context;

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewRichDrawable, defStyleAttr, defStyleRes);

        try {
            mDrawableWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableWidth, UNDEFINED);
            mDrawableHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableHeight, UNDEFINED);
            mDrawableStartVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableStartVector, UNDEFINED);
            mDrawableTopVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableTopVector, UNDEFINED);
            mDrawableEndVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableEndVector, UNDEFINED);
            mDrawableBottomVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableBottomVector, UNDEFINED);
            mDrawableTint = array.getColor(R.styleable.TextViewRichDrawable_drawableTint, UNDEFINED);
        } finally {
            array.recycle();
        }
    }

    public void apply(TextView textView) {
        if (mDrawableWidth > 0 || mDrawableHeight > 0 || mDrawableStartVectorId > 0 || mDrawableTopVectorId > 0
                || mDrawableEndVectorId > 0 || mDrawableBottomVectorId > 0) {
            initCompoundDrawables(textView, mDrawableStartVectorId, mDrawableTopVectorId,
                    mDrawableEndVectorId, mDrawableBottomVectorId);
        }
    }

    private void initCompoundDrawables(TextView textView, int drawableStartVectorId, int drawableTopVectorId,
                                       int drawableEndVectorId, int drawableBottomVectorId) {
        Drawable[] drawables = textView.getCompoundDrawables();

        inflateVectors(textView, drawableStartVectorId, drawableTopVectorId, drawableEndVectorId,
                drawableBottomVectorId, drawables);
        scale(drawables);
        tint(drawables);

        textView.setCompoundDrawables(drawables[LEFT_DRAWABLE_INDEX], drawables[TOP_DRAWABLE_INDEX],
                drawables[RIGHT_DRAWABLE_INDEX], drawables[BOTTOM_DRAWABLE_INDEX]);
    }

    private void inflateVectors(TextView textView, int drawableStartVectorId, int drawableTopVectorId,
                                int drawableEndVectorId, int drawableBottomVectorId, Drawable[] drawables) {
        boolean rtl = ViewCompat.getLayoutDirection(textView) == ViewCompat.LAYOUT_DIRECTION_RTL;

        if (drawableStartVectorId != UNDEFINED) {
            drawables[rtl ? RIGHT_DRAWABLE_INDEX : LEFT_DRAWABLE_INDEX] = getVectorDrawable(drawableStartVectorId);
        }
        if (drawableTopVectorId != UNDEFINED) {
            drawables[TOP_DRAWABLE_INDEX] = getVectorDrawable(drawableTopVectorId);
        }
        if (drawableEndVectorId != UNDEFINED) {
            drawables[rtl ? LEFT_DRAWABLE_INDEX : RIGHT_DRAWABLE_INDEX] = getVectorDrawable(drawableEndVectorId);
        }
        if (drawableBottomVectorId != UNDEFINED) {
            drawables[BOTTOM_DRAWABLE_INDEX] = getVectorDrawable(drawableBottomVectorId);
        }
    }

    private void scale(Drawable[] drawables) {
        if (mDrawableHeight > 0 || mDrawableWidth > 0) {
            for (Drawable drawable : drawables) {
                if (drawable == null) {
                    continue;
                }

                Rect realBounds = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                float actualDrawableWidth = realBounds.width();
                float actualDrawableHeight = realBounds.height();
                float actualDrawableRatio = actualDrawableHeight / actualDrawableWidth;

                float scale;
                // check if both width and height defined then adjust drawable size according to the ratio
                if (mDrawableHeight > 0 && mDrawableWidth > 0) {
                    float placeholderRatio = mDrawableHeight / (float) mDrawableWidth;
                    if (placeholderRatio > actualDrawableRatio) {
                        scale = mDrawableWidth / actualDrawableWidth;
                    } else {
                        scale = mDrawableHeight / actualDrawableHeight;
                    }
                } else if (mDrawableHeight > 0) { // only height defined
                    scale = mDrawableHeight / actualDrawableHeight;
                } else { // only width defined
                    scale = mDrawableWidth / actualDrawableWidth;
                }

                actualDrawableWidth = actualDrawableWidth * scale;
                actualDrawableHeight = actualDrawableHeight * scale;

                realBounds.right = realBounds.left + Math.round(actualDrawableWidth);
                realBounds.bottom = realBounds.top + Math.round(actualDrawableHeight);

                drawable.setBounds(realBounds);
            }
        } else {
            for (Drawable drawable : drawables) {
                if (drawable == null) {
                    continue;
                }

                drawable.setBounds(new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()));
            }
        }
    }

    private void tint(Drawable[] drawables) {
        if (mDrawableTint != UNDEFINED) {
            for (int i = 0; i < drawables.length; i++) {
                if (drawables[i] == null) {
                    continue;
                }

                Drawable tintedDrawable = DrawableCompat.wrap(drawables[i]);
                DrawableCompat.setTint(tintedDrawable.mutate(), mDrawableTint);

                drawables[i] = tintedDrawable;
            }
        }
    }

    private Drawable getVectorDrawable(@DrawableRes int resId) {
        return ResourcesCompat.getDrawable(mContext.getResources(), resId, mContext.getTheme());
    }

    /**
     * {@inheritDoc}
     */
    public int getCompoundDrawableHeight() {
        return mDrawableHeight;
    }

    /**
     * {@inheritDoc}
     */
    public int getCompoundDrawableWidth() {
        return mDrawableWidth;
    }


    @Override
    public void setDrawableStartVectorId(@DrawableRes int id) {
        mDrawableStartVectorId = id;
    }

    @Override
    public void setDrawableEndVectorId(@DrawableRes int id) {
        mDrawableEndVectorId = id;
    }

    @Override
    public void setDrawableTopVectorId(@DrawableRes int id) {
        mDrawableTopVectorId = id;
    }

    @Override
    public void setDrawableBottomVectorId(@DrawableRes int id) {
        mDrawableBottomVectorId = id;
    }
}
