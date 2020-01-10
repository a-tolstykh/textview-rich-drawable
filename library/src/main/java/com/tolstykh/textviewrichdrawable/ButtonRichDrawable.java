package com.tolstykh.textviewrichdrawable;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatButton;
import android.util.AttributeSet;

import com.tolstykh.textviewrichdrawable.helper.RichDrawableHelper;

public class ButtonRichDrawable extends AppCompatButton implements DrawableEnriched {

    private RichDrawableHelper mRichDrawableHelper;

    public ButtonRichDrawable(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ButtonRichDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ButtonRichDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mRichDrawableHelper.setDrawableBottomVectorId(id);
        mRichDrawableHelper.apply(this);
    }
}
