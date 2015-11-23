package com.sonaive.expandwrapkeyboarddemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by liutao on 11/17/15.
 */
public class ScrollLayout extends FrameLayout {
    private ListView mListView;

    public ScrollLayout(Context context) {
        this(context, null);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mListView = (ListView) getChildAt(0);
    }

    public void beginScroll(int offset){
        mListView.smoothScrollBy(offset, 500);
        invalidate();
    }
}
