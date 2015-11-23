package com.sonaive.expandwrapkeyboarddemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by liutao on 11/20/15.
 */
public class SmoothShiftingListView extends ListView {

    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int STATUS_SHIFTED = 2;
    public static final int STATUS_NOT_SHIFTED = 3;

    private float mShiftingDistance;
    private int mStatus;
    private int mAnimTime = 500;

    public SmoothShiftingListView(Context context) {
        super(context);
    }

    public SmoothShiftingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setShiftingDistance(float shiftDistance) {
        mShiftingDistance = shiftDistance;
    }

    public void shift(int direction) {
        shift(direction, mAnimTime);
    }

    public void shift(int direction, int time) {
        mAnimTime = time;
        ObjectAnimator mAnimator;
        if (direction == DIRECTION_UP) {
            mAnimator = ObjectAnimator.ofFloat(this, "height", 1000, 400);
        } else {
            mAnimator = ObjectAnimator.ofFloat(this, "translationY", -900, 0);
        }
        mAnimator.setDuration(mAnimTime);
        mAnimator.start();
        invalidate();
        mStatus = direction == DIRECTION_UP ? STATUS_SHIFTED : STATUS_NOT_SHIFTED;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mStatus == STATUS_SHIFTED) {
            shift(DIRECTION_DOWN, mAnimTime);
            return true;
        }
        return super.onTouchEvent(event);
    }
}
