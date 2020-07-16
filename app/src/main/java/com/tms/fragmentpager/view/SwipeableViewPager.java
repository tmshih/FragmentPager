package com.tms.fragmentpager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tms.fragmentpager.R;


/**
 * Created by TsungMu on 2016/7/1.
 */
public class SwipeableViewPager extends ViewPager {

    protected boolean isSwipeable;

    public SwipeableViewPager(Context context) {
        super(context);
        setSwipeable(true);
    }

    public SwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typeAttr = context.obtainStyledAttributes(attrs, R.styleable.SwipeableViewPager);
        try {
            setSwipeable(typeAttr.getBoolean(R.styleable.SwipeableViewPager_swipeable, false));
        } finally {
            typeAttr.recycle();
        }
    }

    /** Set to enable or disable swipe. **/
    public void setSwipeable(boolean flag) {
        isSwipeable = flag;
    }

    @Override
    public boolean arrowScroll(int direction) {
        if (isSwipeable) {
            return super.arrowScroll(direction);
        }
        return (direction == FOCUS_LEFT || direction == FOCUS_RIGHT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSwipeable ? super.onInterceptTouchEvent(ev) : false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isSwipeable ? super.onTouchEvent(ev) : false;
    }
}
