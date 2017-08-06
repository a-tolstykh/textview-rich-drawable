package com.tolstykh.textviewrichdrawable;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.tolstykh.textviewrichdrawable.helper.RichDrawableHelper;

public class CheckBoxRichDrawable extends AppCompatCheckBox implements DrawableEnriched {

    private RichDrawableHelper mRichDrawableHelper;

    public CheckBoxRichDrawable(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CheckBoxRichDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public CheckBoxRichDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mRichDrawableHelper = new RichDrawableHelper(context, attrs, defStyleAttr, defStyleRes);
        mRichDrawableHelper.apply(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCompoundDrawableHeight() {
        return mRichDrawableHelper.getCompoundDrawableHeight();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCompoundDrawableWidth() {
        return mRichDrawableHelper.getCompoundDrawableWidth();
    }

    @Override
    public void setDrawableStartVectorId(@DrawableRes int id) {
        mRichDrawableHelper.setDrawableStartVectorId(id);
        mRichDrawableHelper.apply(this);
    }

    @Override
    public void setDrawableEndVectorId(@DrawableRes int id) {
        mRichDrawableHelper.setDrawableEndVectorId(id);
        mRichDrawableHelper.apply(this);
    }

    @Override
    public void setDrawableTopVectorId(@DrawableRes int id) {
        mRichDrawableHelper.setDrawableTopVectorId(id);
        mRichDrawableHelper.apply(this);
    }

    @Override
    public void setDrawableBottomVectorId(@DrawableRes int id) {
        mRichDrawableHelper.setDrawableTopVectorId(id);
        mRichDrawableHelper.apply(this);
    }
}
