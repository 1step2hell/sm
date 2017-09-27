package com.step2hell.newsmth.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.ui.main.MainActivity;
import com.step2hell.newsmth.widget.TitleCenterInsideToolbar;

public class BaseActivity extends AppCompatActivity {

    private TitleCenterInsideToolbar mToolbar;

    public void initToolbar() {
        mToolbar = (TitleCenterInsideToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


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
