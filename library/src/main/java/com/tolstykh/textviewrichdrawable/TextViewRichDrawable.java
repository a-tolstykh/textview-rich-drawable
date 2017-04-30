package com.tolstykh.textviewrichdrawable;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.tolstykh.textviewrichdrawable.helper.RichDrawableHelper;

public class TextViewRichDrawable extends AppCompatTextView implements DrawableEnriched {

    private RichDrawableHelper mRichDrawableHelper;

    public TextViewRichDrawable(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public TextViewRichDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public TextViewRichDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
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
}
