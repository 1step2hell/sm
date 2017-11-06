package com.step2hell.newsmth.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Be careful of the difference between NetworkInfo.isConnected() and NetworkInfo.isAvailable().
 * NetworkInfo is available doesn't mean network is connected, it's only a possibility.
 * Connected => Available, Available ≠> Connected.
 * Connected ≠> can pass data, it is possible to establish connections and pass data.
 */
public class NetworkUtil {


    /*------------------------------ Section of NetworkInfo Connected ------------------------------*/

    public static boolean isNetworkConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null){
            return info.isConnected();
        }
        return false;
    }

    public static boolean isWifiConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null){
            return wifiInfo.isConnected();
        }
        return false;
    }

    public static boolean isMobileConnected(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileInfo != null){
            return mobileInfo.isConnected();
        }
        return false;
    }

    /**
     *
     * @param context
     * @return  -1  :   ConnectivityManager.TYPE_NONE
     *          0   :   ConnectivityManager.TYPE_MOBILE
     *          1   :   ConnectivityManager.TYPE_WIFI
     *          6   :   ConnectivityManager.TYPE_WIMAX
     *          7   :   ConnectivityManager.TYPE_BLUETOOTH
     *          9   :   ConnectivityManager.TYPE_ETHERNET
     *          17  :   ConnectivityManager.TYPE_VPN
     */
    public static int getConnectedType(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()){
            return info.getType();
        }
        return -1;
    }

    public static boolean isStableNetworkConnected(Context context){
        int type = getConnectedType(context);
        switch (type){
            case ConnectivityManager.TYPE_MOBILE:
            case ConnectivityManager.TYPE_WIFI:
            case ConnectivityManager.TYPE_WIMAX:
            case ConnectivityManager.TYPE_BLUETOOTH:
            case ConnectivityManager.TYPE_ETHERNET:
            case ConnectivityManager.TYPE_VPN:
                return true;
            default:
                break;
        }
        return false;
    }


    /*------------------------------ Section of NetworkInfo Available ------------------------------*/

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null){
            return info.isAvailable();
        }
        return false;
    }

    public static boolean isWifiAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null){
            return wifiInfo.isConnected();
        }
        return false;
    }

    public static boolean isMobileAvailable(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileInfo != null){
            return mobileInfo.isAvailable();
        }
        return false;
    }

    /**
     *
     * @param context
     * @return  -1  :   ConnectivityManager.TYPE_NONE
     *          0   :   ConnectivityManager.TYPE_MOBILE
     *          1   :   ConnectivityManager.TYPE_WIFI
     *          6   :   ConnectivityManager.TYPE_WIMAX
     *          7   :   ConnectivityManager.TYPE_BLUETOOTH
     *          9   :   ConnectivityManager.TYPE_ETHERNET
     *          17  :   ConnectivityManager.TYPE_VPN
     */
    public static int getAvailableType(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()){
            return info.getType();
        }
        return -1;
    }

    public static boolean isStableNetworkAvailable(Context context){
        int type = getAvailableType(context);
        switch (type){
            case ConnectivityManager.TYPE_MOBILE:
            case ConnectivityManager.TYPE_WIFI:
            case ConnectivityManager.TYPE_WIMAX:
            case ConnectivityManager.TYPE_BLUETOOTH:
            case ConnectivityManager.TYPE_ETHERNET:
            case ConnectivityManager.TYPE_VPN:
                return true;
            default:
                break;
        }
        return false;
    }

}
