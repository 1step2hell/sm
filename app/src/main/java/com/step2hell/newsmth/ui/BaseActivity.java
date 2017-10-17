package com.step2hell.newsmth.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.ui.main.MainActivity;
import com.step2hell.newsmth.util.NetworkUtil;
import com.step2hell.newsmth.widget.TitleCenterInsideToolbar;

public class BaseActivity extends AppCompatActivity {


    /*------------------------------ Observe network ------------------------------*/

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
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectivityReceiver != null) unregisterReceiver(connectivityReceiver);
    }


    /*------------------------------ Setup Toolbar ------------------------------*/

    protected TitleCenterInsideToolbar mToolbar;

    public void setupToolbar() {
        mToolbar = (TitleCenterInsideToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    /*------------------------------ Double tap to exit quickly ------------------------------*/

    protected static long firstTapTime = 0;
    protected static final String TAG_EXIT = "tag_exit";

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTapTime > 400) {
                firstTapTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(TAG_EXIT, true);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
