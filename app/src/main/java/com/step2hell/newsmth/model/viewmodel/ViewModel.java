package com.step2hell.newsmth.model.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.step2hell.newsmth.R;
import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.model.datamodel.DataModel;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class ViewModel {
    /**
     * 静态内部类单例模式
     * 不使用枚举单例是因为枚举不支持继承,后续使用Databinding,需要ViewModel继承BaseObservable.
     */
    private static class LazyHolder {
        private final static ViewModel INSTANCE = new ViewModel();
    }

    public final static ViewModel getInstance() {
        return LazyHolder.INSTANCE;
    }


    /**
     * 获取进站广告
     *
     * @return
     */
    public Observable<AdvBean> fetchAdv() {
        return DataModel.ADV.fetch();
    }

    @BindingAdapter({"bind:adv"})
    public static void setupAdv(final ImageView view, Observable<AdvBean> observable) {
        observable.subscribe(new Consumer<AdvBean>() {
            @Override
            public void accept(@NonNull final AdvBean bean) throws Exception {
                RequestOptions options = new RequestOptions()
                        .fitCenter()
                        .placeholder(R.mipmap.ic_launcher)
                        .transform(new RoundedCorners(8));
                Glide.with(view.getContext())
                        .load(bean.getFile())
                        .apply(options)
                        .into(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(view.getContext(), bean.getUrl(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
