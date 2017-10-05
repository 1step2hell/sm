package com.step2hell.newsmth.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.step2hell.newsmth.R;
import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.model.viewmodel.ViewModel;
import com.step2hell.newsmth.ui.BaseActivity;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class AdvActivity extends BaseActivity {

    private CompositeDisposable disposable;

    private ImageView advImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv);

        initToolbar();

        // Todo: 判断网络先
        disposable = new CompositeDisposable();
        setupViews();
    }

    private void setupViews() {
        advImg = (ImageView) findViewById(R.id.adv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        disposable.add(ViewModel.getInstance().fetchAdv().subscribe(new Consumer<AdvBean>() {
            @Override
            public void accept(@NonNull AdvBean advBean) throws Exception {
                setAdvImg(advBean);
            }
        }));
    }

    private void unBind() {
        disposable.clear();
    }

    private void setAdvImg(final AdvBean bean) {
        assert bean != null;
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .transform(new RoundedCorners(8));
        Glide.with(this)
                .load("http://images.newsmth.net/nForum" + bean.getFile())
                .apply(options)
                .into(advImg);
        advImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdvActivity.this, bean.getUrl(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
