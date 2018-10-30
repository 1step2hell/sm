package com.step2hell.newsmth.util;

import android.content.Context;

public class LeakCanaryTestSingleTon {
    private static LeakCanaryTestSingleTon singleTon;

    private Context context;

    private LeakCanaryTestSingleTon(Context context) {
        this.context = context;
    }

    public static LeakCanaryTestSingleTon getInstance(Context context) {
        if (singleTon == null) {
            synchronized (LeakCanaryTestSingleTon.class) {
                if (singleTon == null) {
                    singleTon = new LeakCanaryTestSingleTon(context);
                }
            }
        }
        return singleTon;
    }
}
