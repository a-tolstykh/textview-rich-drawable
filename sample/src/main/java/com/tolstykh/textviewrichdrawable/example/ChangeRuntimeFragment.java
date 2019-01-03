package com.tolstykh.textviewrichdrawable.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolstykh.textviewrichdrawable.R;
import com.tolstykh.textviewrichdrawable.TextViewRichDrawable;

/**
 * * Created by rqg on 2019/1/3.
 */
public class ChangeRuntimeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_runtime_change, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextViewRichDrawable tvrd = view.findViewById(R.id.tv_demo);

        view.findViewById(R.id.set_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvrd.setTopDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_shz_skrz));
            }
        });

        view.findViewById(R.id.set_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvrd.setBottomDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_shz_skrz));
            }
        });

        view.findViewById(R.id.set_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvrd.setLeftDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_shz_skrz));
            }
        });

        view.findViewById(R.id.set_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvrd.setRightDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_shz_skrz));
            }
        });

    }
}
