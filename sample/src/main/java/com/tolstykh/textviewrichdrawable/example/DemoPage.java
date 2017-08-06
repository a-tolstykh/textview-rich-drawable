package com.tolstykh.textviewrichdrawable.example;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.tolstykh.textviewrichdrawable.R;

enum DemoPage {
    TEXT_VIEWS(R.string.title_text_views_demo) {
        @Override
        Fragment instantiateFragment() {
            return TextViewsFragment.newInstance();
        }
    },
    ALL_CONTROLS(R.string.title_all_controls_demo) {
        @Override
        Fragment instantiateFragment() {
            return SimpleFragment.newInstance(R.layout.f_controls_all);
        }
    };

    @StringRes
    private final int mTitleResId;

    DemoPage(int titleResId) {
        mTitleResId = titleResId;
    }

    @StringRes
    int getTitleResId() {
        return mTitleResId;
    }

    abstract Fragment instantiateFragment();
}

