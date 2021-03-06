package com.step2hell.newsmth.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.ui.main.MainActivity;
import com.step2hell.newsmth.widget.TitleCenterInsideToolbar;

public class BaseActivity extends AppCompatActivity {


    /*------------------------------ Set Orientation ------------------------------*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
