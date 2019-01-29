package com.step2hell.newsmth.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;

public final class SizeUtil {

    public static int getScreenWidthPx() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeightPx() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.heightPixels;
    }

    // 测量尺寸以建适配文件夹sw<N>dp
    public static int getScreenWidthDp() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        Log.d("SizeUtil", "widthPixels:" + metrics.widthPixels);
        Log.d("SizeUtil", "density:" + metrics.density);
        Log.d("SizeUtil", "densityDpi:" + metrics.densityDpi);
        return Math.round(metrics.widthPixels / metrics.density);
    }

    public static int getScreenWidthDp(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.d("SizeUtil", "widthPixels:" + metrics.widthPixels);
        Log.d("SizeUtil", "density:" + metrics.density);
        Log.d("SizeUtil", "densityDpi:" + metrics.densityDpi);
        return Math.round(metrics.widthPixels / metrics.density);
//        return (int) (metrics.widthPixels / metrics.density + 0.5f);
    }

    public static int getScreenHeightDp() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return Math.round(metrics.heightPixels / metrics.density);
    }

    public static int getStatusBarHeightPx(Activity activity){
        int statusBarHeight = 0;
        // Todo
        return statusBarHeight;
    }

    public static int getTitleBarHeightPx(Activity activity) {
        Window window = activity.getWindow();
        int decorTop = window.getDecorView().getTop();
        int contentTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentTop - decorTop;
        return titleBarHeight;
    }

    public static int dp2px(int dp) {
        return Math.round(Resources.getSystem().getDisplayMetrics().density * dp);
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics()));
    }

    public static int px2dp(int px) {
        return Math.round(px / (Resources.getSystem().getDisplayMetrics().density));
    }


    public static int sp2px(float sp) {
        return Math.round(Resources.getSystem().getDisplayMetrics().scaledDensity * sp);
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics()));
    }

    public static int px2sp(float px) {
        return Math.round(px / (Resources.getSystem().getDisplayMetrics().scaledDensity));
    }
}
