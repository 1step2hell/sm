package com.step2hell.newsmth.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.step2hell.newsmth.R;
import com.step2hell.newsmth.model.bean.AdBean;

public class AdDialog extends Dialog implements View.OnClickListener {

    private ImageView imageView;
    private AdBean bean;

    public AdDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialog_ad);
        imageView = findViewById(R.id.image);
        imageView.setOnClickListener(this);
        findViewById(R.id.badge).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getWindow();
        window.getAttributes().dimAmount = 0.2f;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
    }

    public void setAd(AdBean bean){
        this.bean = bean;
        loadImage(bean.getImage());
    }

    private void loadImage(String imageUrl) {
        Glide.with(getContext())
                .load(imageUrl)
                .apply(new RequestOptions()
                        .fitCenter()
                        .placeholder(R.mipmap.ic_launcher)
                        .transform(new RoundedCorners(1 << 3)))
                .into(imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.badge:
                dismiss();
                break;
            case R.id.image:
                // Todo :
                Toast.makeText(getContext(),bean.getArticle(),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
