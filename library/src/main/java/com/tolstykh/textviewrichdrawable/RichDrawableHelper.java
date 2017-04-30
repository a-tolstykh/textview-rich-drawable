package com.tolstykh.textviewrichdrawable;

import android.content.Context;
import android.content.res.Resources;
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

public class RichDrawableHelper implements DrawableEnriched {

    private static final int LEFT_DRAWABLE_INDEX = 0;
    private static final int TOP_DRAWABLE_INDEX = 1;
    private static final int RIGHT_DRAWABLE_INDEX = 2;
    private static final int BOTTOM_DRAWABLE_INDEX = 3;

    private TextView mView;

    private int mDrawableWidth;
    private int mDrawableHeight;
    @ColorInt
    private int mDrawableTint;

    public RichDrawableHelper(@NonNull TextView view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        mView = view;

        TypedArray array = view.getContext()
                .obtainStyledAttributes(attrs, R.styleable.TextViewRichDrawable, defStyleAttr, defStyleRes);

        int drawableStartVectorId;
        int drawableTopVectorId;
        int drawableEndVectorId;
        int drawableBottomVectorId;
        try {
            mDrawableWidth = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableWidth, UNDEFINED);
            mDrawableHeight = array.getDimensionPixelSize(R.styleable.TextViewRichDrawable_compoundDrawableHeight, UNDEFINED);
            drawableStartVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableStartVector, UNDEFINED);
            drawableTopVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableTopVector, UNDEFINED);
            drawableEndVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableEndVector, UNDEFINED);
            drawableBottomVectorId = array.getResourceId(R.styleable.TextViewRichDrawable_drawableBottomVector, UNDEFINED);
            mDrawableTint = array.getColor(R.styleable.TextViewRichDrawable_drawableTint, UNDEFINED);
        } finally {
            array.recycle();
        }

        if (mDrawableWidth > 0 || mDrawableHeight > 0 || drawableStartVectorId > 0 || drawableTopVectorId > 0
                || drawableEndVectorId > 0 || drawableBottomVectorId > 0) {
            initCompoundDrawables(drawableStartVectorId, drawableTopVectorId, drawableEndVectorId, drawableBottomVectorId);
        }
    }

    private void initCompoundDrawables(int drawableStartVectorId, int drawableTopVectorId,
                                       int drawableEndVectorId, int drawableBottomVectorId) {
        Drawable[] drawables = mView.getCompoundDrawables();

        boolean rtl = ViewCompat.getLayoutDirection(mView) == ViewCompat.LAYOUT_DIRECTION_RTL;

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

        if (mDrawableHeight > 0 || mDrawableWidth > 0) {
            for (Drawable drawable : drawables) {
                if (drawable == null) {
                    continue;
                }

                Rect realBounds = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                float scaleFactor = realBounds.height() / (float) realBounds.width();

                float drawableWidth = realBounds.width();
                float drawableHeight = realBounds.height();

                if (mDrawableWidth > 0) {
                    // save scale factor of image
                    if (drawableWidth > mDrawableWidth) {
                        drawableWidth = mDrawableWidth;
                        drawableHeight = drawableWidth * scaleFactor;
                    }
                }
                if (mDrawableHeight > 0) {
                    // save scale factor of image

                    if (drawableHeight > mDrawableHeight) {
                        drawableHeight = mDrawableHeight;
                        drawableWidth = drawableHeight / scaleFactor;
                    }
                }

                realBounds.right = realBounds.left + Math.round(drawableWidth);
                realBounds.bottom = realBounds.top + Math.round(drawableHeight);

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

        mView.setCompoundDrawables(drawables[LEFT_DRAWABLE_INDEX], drawables[TOP_DRAWABLE_INDEX],
                drawables[RIGHT_DRAWABLE_INDEX], drawables[BOTTOM_DRAWABLE_INDEX]);
    }

    private Resources getResources() {
        return mView.getResources();
    }

    private Context getContext() {
        return mView.getContext();
    }

    private VectorDrawableCompat getVectorDrawable(@DrawableRes int resId) {
        return VectorDrawableCompat.create(getResources(), resId, getContext().getTheme());
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
}
