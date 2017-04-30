package com.tolstykh.textviewrichdrawable.example;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DemoPagesAdapter extends PagerAdapter {

    private final DemoPage[] mDemoPages;
    private final Context mContext;

    public DemoPagesAdapter(Context context, DemoPage[] demoPages) {
        mContext = context;
        mDemoPages = demoPages;
    }

    @Override
    public int getCount() {
        return mDemoPages.length;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        DemoPage demoPage = mDemoPages[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(demoPage.getLayoutId(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(mDemoPages[position].getTitleResId());
    }
}
