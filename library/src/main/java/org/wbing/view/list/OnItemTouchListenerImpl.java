package org.wbing.view.list;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author wangbing
 * @date 2018/8/21
 */
public class OnItemTouchListenerImpl implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat gestureDetector;

    public OnItemTouchListenerImpl(Context context, GestureDetector.OnGestureListener gestureListener) {
        this.gestureDetector = new GestureDetectorCompat(context, gestureListener);
    }

    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        this.onTouchEvent(rv, e);
        return false;
    }

    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        this.gestureDetector.onTouchEvent(e);
    }

    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
