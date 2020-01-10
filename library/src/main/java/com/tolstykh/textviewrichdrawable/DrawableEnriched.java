package com.tolstykh.textviewrichdrawable;

import androidx.annotation.DrawableRes;

public interface DrawableEnriched {

    int UNDEFINED = -1;

    /**
     * Returns the compound drawable height of this view.
     *
     * @return the height in pixels or {@value UNDEFINED} if undefined.
     */
    int getCompoundDrawableHeight();

    /**
     * Returns the compound drawable width of this view.
     *
     * @return the width in pixels or {@value UNDEFINED} if undefined.
     */
    int getCompoundDrawableWidth();

    void setDrawableStartVectorId(@DrawableRes int id);

    void setDrawableEndVectorId(@DrawableRes int id);

    void setDrawableTopVectorId(@DrawableRes int id);

    void setDrawableBottomVectorId(@DrawableRes int id);
}
