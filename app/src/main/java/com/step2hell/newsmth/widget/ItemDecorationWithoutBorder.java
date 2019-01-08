package com.step2hell.newsmth.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 不带边缘分割线的ItemDecoration
 * 目前仅支持{@link LinearLayoutManager}和{@link GridLayoutManager}
 * 不支持{@link StaggeredGridLayoutManager}以及自定义{@link RecyclerView.LayoutManager}
 */
public class ItemDecorationWithoutBorder extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private Drawable divider;

    private int dividerWidth;

    private int orientation;

    private final Rect bounds = new Rect();


    public ItemDecorationWithoutBorder(Context context, int orientation) {
        divider = new ColorDrawable(Color.parseColor("#EEEEEE"));
        dividerWidth = 1;
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.orientation = orientation;
    }

    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        divider = drawable;
    }

    public void setWidth(int width) {
        dividerWidth = width;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if (orientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        int spanCount;
        if (manager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) manager).getSpanCount();
        } else if (manager instanceof LinearLayoutManager) {
            spanCount = 1;
        } else {
            // StaggeredGridLayoutManager or other custom LayoutManager
            spanCount = 0;
        }
        for (int i = 0; i < childCount - spanCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, bounds);
            final int bottom = bounds.bottom + Math.round(ViewCompat.getTranslationY(child));
            final int top = bottom - dividerWidth;
            divider.setBounds(left, top, right, bottom);
            divider.draw(canvas);
        }
        canvas.restore();
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) manager).getSpanCount();
            for (int i = 0; i < childCount; i++) {
                if ((i + 1) % spanCount != 0) {
                    final View child = parent.getChildAt(i);
                    parent.getLayoutManager().getDecoratedBoundsWithMargins(child, bounds);
                    final int right = bounds.right + Math.round(child.getTranslationX());
                    final int left = right - dividerWidth;
                    divider.setBounds(left, top, right, bottom);
                    divider.draw(canvas);
                }
            }
        } else if (manager instanceof LinearLayoutManager) {
            for (int i = 0; i < childCount - 1; i++) {
                final View child = parent.getChildAt(i);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, bounds);
                final int right = bounds.right + Math.round(child.getTranslationX());
                final int left = right - dividerWidth;
                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            }
        } else {
            // StaggeredGridLayoutManager or other custom LayoutManager
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                parent.getLayoutManager().getDecoratedBoundsWithMargins(child, bounds);
                final int right = bounds.right + Math.round(child.getTranslationX());
                final int left = right - dividerWidth;
                divider.setBounds(left, top, right, bottom);
                divider.draw(canvas);
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        int count = manager.getItemCount();     // 不等同于onDraw()使用的manager.getChildCount()
        int index = manager.getPosition(view);
        if (manager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) manager).getSpanCount();
            if (orientation == VERTICAL) {
                int mod = count % spanCount;
                int lastLineItemCount = mod == 0 ? spanCount : mod;
                if (index + lastLineItemCount < count) {
                    outRect.set(0, 0, 0, dividerWidth);
                } else {
                    outRect.setEmpty();
                }
            } else {
                if ((index + 1) % spanCount != 0) {
                    outRect.set(0, 0, dividerWidth, 0);
                } else {
                    outRect.setEmpty();
                }
            }
        } else if (manager instanceof LinearLayoutManager) {
            if (index != count - 1) {
                if (orientation == VERTICAL) {
                    outRect.set(0, 0, 0, dividerWidth);
                } else {
                    outRect.set(0, 0, dividerWidth, 0);
                }
            } else {
                outRect.setEmpty();
            }
        } else {
            // StaggeredGridLayoutManager or other custom LayoutManager
            if (orientation == VERTICAL) {
                outRect.set(0, 0, 0, dividerWidth);
            } else {
                outRect.set(0, 0, dividerWidth, 0);
            }
        }
    }
}