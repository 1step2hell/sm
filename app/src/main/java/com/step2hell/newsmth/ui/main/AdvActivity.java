package com.step2hell.newsmth.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.databinding.ActivityAdvBinding;
import com.step2hell.newsmth.model.viewmodel.ViewModel;
import com.step2hell.newsmth.ui.BaseActivity;


public class AdvActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdvBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_adv);
        binding.setViewModel(ViewModel.getInstance());
        setupToolbar();
    }

}
