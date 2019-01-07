package com.step2hell.newsmth.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *  ios风格的加载菊花
 */
class LoadingView extends View {

    private Context context;

    private int width;
    private int height;

    private int petalWidth;
    private int petalHeight;
    private int petalRadius;

    private Paint paint;
    private RectF rect;
    private RectF ovalTop;
    private RectF ovalBottom;

    private int index;
    private int[] colors = new int[]{
            0xFFFFFFFF,     // 白色 100%不透明度
            0xE6FFFFFF,     // 白色 90%不透明度
            0xCDFFFFFF,     // 白色 80%不透明度
            0xB3FFFFFF,     // 白色 70%不透明度
            0x9aFFFFFF,     // 白色 60%不透明度
            0x80FFFFFF,     // 白色 50%不透明度
            0x66FFFFFF,     // 白色 40%不透明度
            0x4DFFFFFF      // 白色 30%不透明度
    };


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            int size = dp2px(context, 28);  // 默认尺寸28dp
            width = size;
            height = size;
        } else {
            int size = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
            width = size;
            height = size;
        }

        // 获取起始竖立花瓣的尺寸
        petalHeight = height >> 2;            // 花瓣高度为LoadingView的1/4
        petalWidth = (petalHeight << 1) / 7;  // 花瓣宽高比为2：7
        petalRadius = petalWidth >> 1;        // 花瓣两端半径为宽度一半（两端半圆形）
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < colors.length; i++) {
            paint.setColor(colors[(i + index) % colors.length]);
            drawTopArc(canvas);
            drawMidRect(canvas);
            drawBottomArc(canvas);
            canvas.rotate(-360 / colors.length, width >> 1, height >> 1);
        }
        index = ++index % colors.length;
        postInvalidateDelayed(200); // 花瓣换色间隔0.2秒
    }

    private void drawMidRect(Canvas canvas) {
        if (rect == null) {
            rect = new RectF((width - petalWidth) >> 1, petalRadius,
                    (width + petalWidth) >> 1, petalHeight - petalRadius);
        }
        canvas.drawRect(rect, paint);
    }

    private void drawTopArc(Canvas canvas) {
        if (ovalTop == null) {
            ovalTop = new RectF((width - petalWidth) >> 1, 0, (width + petalWidth) >> 1, petalRadius << 1);
        }
        canvas.drawArc(ovalTop, 180, 180, true, paint);
    }

    private void drawBottomArc(Canvas canvas) {
        if (ovalBottom == null) {
            ovalBottom = new RectF((width - petalWidth) >> 1, petalHeight - (petalRadius << 1),
                    (width + petalWidth) >> 1, petalHeight);
        }
        canvas.drawArc(ovalBottom, 0, 180, true, paint);
    }

    private int dp2px(Context context, float dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }
}
