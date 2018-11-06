package com.step2hell.newsmth.ui.main;

import android.os.Bundle;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.ui.BaseActivity;

public class TestLeakCanaryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_leak_canary);
        setupToolbar();

        // test LeakCanary
        TestLeakCanarySingleTon.getInstance(this).foo();
    }
}
