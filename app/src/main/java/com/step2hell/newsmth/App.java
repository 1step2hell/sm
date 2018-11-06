package com.step2hell.newsmth;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.squareup.leakcanary.LeakCanary;
import com.step2hell.newsmth.util.NetworkUtil;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * http://blog.csdn.net/qq284565035/article/details/51811590
 * http://www.developerphil.com/no-you-can-not-override-the-home-button-but-you-dont-have-to/
 */
public class App extends Application {

    protected BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtil.isNetworkConnected(context)) {
                // Todo: network is connected, notify everybody with RxBus

            } else {
                // Todo: network is unconnected, notify everybody with RxBus

            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        // init LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        // init Bugly
        CrashReport.initCrashReport(getApplicationContext(), "bec105b4fe", BuildConfig.DEBUG);

        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (connectivityReceiver != null) unregisterReceiver(connectivityReceiver);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
