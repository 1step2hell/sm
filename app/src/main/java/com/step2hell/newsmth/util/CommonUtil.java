package com.step2hell.newsmth.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.step2hell.newsmth.R;

public class CommonUtil {

    public static String getGiphyKey(Context context){
        String key = "";
        try {
            // included in tab <application/> of manifest.
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            key = appInfo.metaData.getString(context.getString(R.string.giphy_key));
            Log.d("Bob", "Giphy key : " + key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }
}
