package com.step2hell.newsmth.widget;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.step2hell.newsmth.R;


public class SwitchPreference extends android.preference.SwitchPreference {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchPreference(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        if (view instanceof Switch) {
            ((Switch) view).setThumbResource(R.drawable.switch_thumb);
            ((Switch) view).setTrackResource(R.drawable.switch_track);
        } else if (view instanceof ViewGroup) {
            Switch theSwitch = findSwitchInChildviews((ViewGroup) view);
            if (theSwitch != null) {
                theSwitch.setThumbResource(R.drawable.switch_thumb);
                theSwitch.setTrackResource(R.drawable.switch_track);
            }
        }
    }

    private Switch findSwitchInChildviews(ViewGroup view) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View thisChildview = view.getChildAt(i);
            if (thisChildview instanceof Switch) {
                return (Switch) thisChildview;
            } else if (thisChildview instanceof ViewGroup) {
                Switch theSwitch = findSwitchInChildviews((ViewGroup) thisChildview);
                if (theSwitch != null) return theSwitch;
            }
        }
        return null;
    }
}
