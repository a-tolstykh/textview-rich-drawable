package com.tolstykh.textviewrichdrawable.example;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoFragment extends Fragment {

    private static final int UNDEFINED = Integer.MIN_VALUE;

    private static final String LAYOUT_RES_KEY = "layout_res";

    public static DemoFragment newInstance(@LayoutRes int layoutResId) {
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES_KEY, layoutResId);

        DemoFragment fragment = new DemoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle == null || bundle.getInt(LAYOUT_RES_KEY, UNDEFINED) == UNDEFINED) {
            throw new IllegalArgumentException("Please use DemoFragment.newInstance(...) method to instantiate fragment");
        }

        int resId = getArguments().getInt(LAYOUT_RES_KEY);
        return inflater.inflate(resId, container, false);
    }
}
