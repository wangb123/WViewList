package org.wbing.view.list;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author wangbing
 * @date 2018/8/21
 */
public class LineItemDecoration extends RecyclerView.ItemDecoration {
    public static int DEF_OFFSET = 1;
    public static int DEF_COLOR = -1250068;
    public static int DEF_PADDING = 0;
    int orientation;
    int offset;
    int paddingLeft;
    int paddingRight;
    private Paint paint;

    public LineItemDecoration() {
        this(DEF_OFFSET);
    }

    public LineItemDecoration(int height) {
        this(height, DEF_COLOR);
    }

    public LineItemDecoration(int height, @ColorInt int color) {
        this(height, color, DEF_PADDING);
    }

    public LineItemDecoration(int height, @ColorInt int color, int padding) {
        this(height, color, padding, padding);
    }

    public LineItemDecoration(int height, @ColorInt int color, int paddingLeft, int paddingRight) {
        this(height, color, paddingLeft, paddingRight, 1);
    }

    public LineItemDecoration(int offset, @ColorInt int color, int paddingLeft, int paddingRight, int orientation) {
        this.offset = offset;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.paddingRight = paddingRight;
        this.orientation = orientation;
        this.paint = new Paint();
        this.paint.setColor(color);
    }

    public LineItemDecoration setOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (this.orientation == 1) {
            outRect.set(0, 0, 0, this.offset);
        } else {
            outRect.set(0, 0, this.offset, 0);
        }

    }

    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (this.orientation == 1) {
            this.drawVertical(c, parent);
        } else {
            this.drawHorizontal(c, parent);
        }

    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() + this.paddingLeft;
        int right = parent.getWidth() - parent.getPaddingRight() - this.paddingRight;
        int childCount = parent.getChildCount() - 1;

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + this.offset;
            c.drawRect((float) left, (float) top, (float) right, (float) bottom, this.paint);
        }

    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop() + this.paddingLeft;
        int bottom = parent.getHeight() - parent.getPaddingBottom() - this.paddingRight;
        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + this.offset;
            c.drawRect((float) left, (float) top, (float) right, (float) bottom, this.paint);
        }

    }
}
