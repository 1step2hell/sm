package com.step2hell.newsmth.ui.main;

import android.content.Context;
import android.util.Log;

public class TestLeakCanarySingleTon {
    private static TestLeakCanarySingleTon sInstance;
    private Context mContext;

    private TestLeakCanarySingleTon(Context context) {
        this.mContext = context;
    }

    public static TestLeakCanarySingleTon getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new TestLeakCanarySingleTon(context);
        }
        return sInstance;
    }

    public void foo(){
        Log.e("Bob","TestLeakCanarySingleTon:"+mContext.toString());
    }
}
