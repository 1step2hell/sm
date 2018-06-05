package com.step2hell.newsmth.model.viewmodel;

import android.databinding.BindingAdapter;
import android.util.Log;
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
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class ViewModel {
    /**
     * 静态内部类单例模式
     * 不使用枚举单例是因为枚举不支持继承,后续使用Databinding,需要ViewModel继承BaseObservable.
     * 如果不需要继承,则可以直接使用枚举单例模式,并把DataModel功能移过来并移除掉DataModel(变成一个纯净的MVVM模式).
     */
    private static class LazyHolder {
        private final static ViewModel INSTANCE = new ViewModel();
    }

    public final static ViewModel getInstance() {
        return LazyHolder.INSTANCE;
    }


    @BindingAdapter("android:src")
    public static Disposable setAdvImageSrc(final ImageView view, Observable<AdvBean> observable) {
        return observable.subscribe(new Consumer<AdvBean>() {
            @Override
            public void accept(@NonNull final AdvBean bean) throws Exception {
                Glide.with(view.getContext())
                        .load(bean.getFile())
                        .apply(new RequestOptions()
                                .fitCenter()
                                .placeholder(R.mipmap.ic_launcher)
                                .transform(new RoundedCorners(1 << 3)))
                        .into(view);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(view.getContext(), bean.getUrl(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(this.getClass().getSimpleName(),"setupAdv:"+throwable.getMessage());
            }
        });
    }

}
