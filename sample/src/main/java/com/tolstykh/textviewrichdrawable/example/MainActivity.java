package com.tolstykh.textviewrichdrawable.example;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
        mPager.setAdapter(new DemoPagesAdapter(this, demoPages));
        mTabLayout.setupWithViewPager(mPager);
    }
}
