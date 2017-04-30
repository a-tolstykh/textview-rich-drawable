package com.tolstykh.textviewrichdrawable.example;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

import com.tolstykh.textviewrichdrawable.R;

enum DemoPage {
    TEXT_VIEWS(R.layout.f_textviews, R.string.title_text_views_demo),
    ALL_CONTROLS(R.layout.f_controls_all, R.string.title_all_controls_demo);

    @LayoutRes
    private final int mLayoutId;
    @StringRes
    private final int mTitleResId;

    DemoPage(int layoutId, int titleResId) {
        mLayoutId = layoutId;
        mTitleResId = titleResId;
    }

    @LayoutRes
    int getLayoutId() {
        return mLayoutId;
    }

    @StringRes
    int getTitleResId() {
        return mTitleResId;
    }
}

