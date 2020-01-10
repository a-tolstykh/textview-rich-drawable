package com.tolstykh.textviewrichdrawable.example;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.tolstykh.textviewrichdrawable.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        findViews();
        initViews();
    }

    private void findViews() {
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void initViews() {
        DemoPage[] demoPages = DemoPage.values();
        mPager.setAdapter(new DemoPagesAdapter(this, getSupportFragmentManager(), demoPages));
        mTabLayout.setupWithViewPager(mPager);
    }
}
