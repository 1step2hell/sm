package com.step2hell.newsmth.ui;

import android.support.v7.app.AppCompatActivity;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.widget.TitleCenterInsideToolbar;

public class BaseActivity extends AppCompatActivity {

    private TitleCenterInsideToolbar mToolbar;

    public void initToolbar(){
        mToolbar = (TitleCenterInsideToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
