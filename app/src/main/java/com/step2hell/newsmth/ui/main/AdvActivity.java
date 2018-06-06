package com.step2hell.newsmth.ui.main;

//import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.step2hell.newsmth.R;
import com.step2hell.newsmth.databinding.ActivityAdvBinding;
import com.step2hell.newsmth.model.viewmodel.ViewModel;
import com.step2hell.newsmth.ui.BaseActivity;
import com.step2hell.newsmth.util.RxBus;

import io.reactivex.disposables.Disposable;


public class AdvActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Conflict with Errorprone if import android.databinding.DataBindingUtil, it will cause NullPointerException.
         * Change databinding method to the other way as below.
         */
        // ActivityAdvBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_adv);

        ActivityAdvBinding binding = ActivityAdvBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.setViewModel(ViewModel.getInstance());
        setupToolbar();

        RxBus.INSTANCE.publish(3);
        RxBus.INSTANCE.publish("haha");
        Disposable disposable = RxBus.INSTANCE.listen(String.class).subscribe(string -> Log.e("Bob", "AdvActivity listen:" + string));
        RxBus.INSTANCE.registerBus(this, disposable);
        RxBus.INSTANCE.publish("hoho");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.INSTANCE.unregisterBus(this);
    }
}
