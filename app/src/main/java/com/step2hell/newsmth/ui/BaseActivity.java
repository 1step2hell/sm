package com.step2hell.newsmth.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.step2hell.newsmth.R;

public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    public void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
