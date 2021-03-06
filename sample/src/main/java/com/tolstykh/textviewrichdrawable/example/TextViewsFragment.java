package com.tolstykh.textviewrichdrawable.example;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolstykh.textviewrichdrawable.R;
import com.tolstykh.textviewrichdrawable.TextViewRichDrawable;

public class TextViewsFragment extends Fragment {

    public static TextViewsFragment newInstance() {
        return new TextViewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_textviews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextViewRichDrawable textView = (TextViewRichDrawable) view.findViewById(R.id.text_set_programmatically);
        textView.setDrawableStartVectorId(R.drawable.ic_android_black_24dp);
    }
}
