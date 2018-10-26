package com.step2hell.newsmth.util;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

public final class ImageUtil {
    public static void setTint(Drawable drawable, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

    public static void setTint(ImageView imageView, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(imageView.getDrawable(), color);
        } else {
            imageView.getDrawable().mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

}
