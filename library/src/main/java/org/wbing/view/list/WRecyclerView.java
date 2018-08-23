package org.wbing.view.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wangbing
 * @date 2018/8/21
 */
public class WRecyclerView extends RecyclerView {
    private WRecyclerView.OnSingleTapListener singleTapListener;
    private WRecyclerView.OnDoubleTapListener doubleTapListener;
    private WRecyclerView.OnLongPressListener longPressListener;
    private boolean initPrivateOnItemTouchListener = false;
    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onSingleTapConfirmed(MotionEvent e) {
            if (WRecyclerView.this.singleTapListener != null) {
                ViewHolder holder = WRecyclerView.this.getViewHolder(e);
                if (holder != null) {
                    return WRecyclerView.this.singleTapListener.onSingleTap(holder);
                }
            }

            return true;
        }

        public void onLongPress(MotionEvent e) {
            if (WRecyclerView.this.longPressListener != null) {
                ViewHolder holder = WRecyclerView.this.getViewHolder(e);
                if (holder != null) {
                    WRecyclerView.this.longPressListener.onLongPress(holder);
                    return;
                }
            }

            super.onLongPress(e);
        }

        public boolean onDoubleTap(MotionEvent e) {
            if (WRecyclerView.this.doubleTapListener != null) {
                ViewHolder holder = WRecyclerView.this.getViewHolder(e);
                if (holder != null) {
                    return WRecyclerView.this.doubleTapListener.onDoubleTap(holder);
                }
            }

            return true;
        }
    };

    public WRecyclerView(Context context) {
        super(context);
    }

    public WRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSingleTapListener(WRecyclerView.OnSingleTapListener singleTapListener) {
        if (!this.initPrivateOnItemTouchListener) {
            this.initPrivateOnItemTouchListener(this.getContext());
        }

        this.singleTapListener = singleTapListener;
    }

    public void setDoubleTapListener(WRecyclerView.OnDoubleTapListener doubleTapListener) {
        if (!this.initPrivateOnItemTouchListener) {
            this.initPrivateOnItemTouchListener(this.getContext());
        }

        this.doubleTapListener = doubleTapListener;
    }

    public void setLongPressListener(WRecyclerView.OnLongPressListener longPressListener) {
        if (!this.initPrivateOnItemTouchListener) {
            this.initPrivateOnItemTouchListener(this.getContext());
        }

        this.longPressListener = longPressListener;
    }

    private void initPrivateOnItemTouchListener(Context context) {
        this.addOnItemTouchListener(new OnItemTouchListenerImpl(context, this.gestureListener));
        this.initPrivateOnItemTouchListener = true;
    }

    private ViewHolder getViewHolder(MotionEvent e) {
        View child = this.findChildViewUnder(e.getX(), e.getY());
        return child != null ? this.getChildViewHolder(child) : null;
    }

    public interface OnDoubleTapListener {
        boolean onDoubleTap(ViewHolder var1);
    }

    public interface OnLongPressListener {
        void onLongPress(ViewHolder var1);
    }

    public interface OnSingleTapListener {
        boolean onSingleTap(ViewHolder var1);
    }
}

