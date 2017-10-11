package com.step2hell.newsmth.ui.main;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.step2hell.newsmth.R;


public class TestPreferenceFragment extends PreferenceFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_data_sync);
    }
}
