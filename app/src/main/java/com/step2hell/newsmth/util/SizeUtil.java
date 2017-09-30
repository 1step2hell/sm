package com.step2hell.newsmth.util;

import android.content.Context;

public class SizeUtil {
    public static int dp2px(Context context, int dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }

    public static int px2dp(Context context, int px) {
        return Math.round(px / (context.getResources().getDisplayMetrics().density));
    }


    public static int sp2px(Context context, float sp) {
        return Math.round(context.getResources().getDisplayMetrics().scaledDensity * sp);
    }

    public static int px2sp(Context context, float px) {
        return Math.round(px / (context.getResources().getDisplayMetrics().scaledDensity));
    }
}
