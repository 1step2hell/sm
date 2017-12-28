package com.step2hell.newsmth.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

public class SizeUtil {

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


    public static int dp2px(Context context, int dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics()));
    }

    public static int px2dp(Context context, int px) {
        return Math.round(px / (context.getResources().getDisplayMetrics().density));
    }


    public static int sp2px(Context context, float sp) {
        return Math.round(context.getResources().getDisplayMetrics().scaledDensity * sp);
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()));
    }

    public static int px2sp(Context context, float px) {
        return Math.round(px / (context.getResources().getDisplayMetrics().scaledDensity));
    }
}
