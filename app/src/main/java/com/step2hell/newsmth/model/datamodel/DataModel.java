package com.step2hell.newsmth.model.datamodel;

import com.google.gson.Gson;
import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.util.HtmlUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public enum DataModel {
    ADV {
        private final static String URL_NEWSMTH = "http://www.newsmth.net/";
        private final static String REG_PREIMG = "preimg=\\[(.*?)\\]";

        @Override
        public Observable<AdvBean> fetch() {
            return new Retrofit.Builder()
                    .baseUrl(URL_NEWSMTH)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(INewsmth.class)
                    .request()
                    .subscribeOn(Schedulers.newThread())
                    .map(new Function<ResponseBody, AdvBean>() {
                        @Override
                        public AdvBean apply(@NonNull ResponseBody responseBody) throws Exception {
                            return new Gson().fromJson(HtmlUtil.getSubSimple(responseBody.string(), REG_PREIMG), AdvBean.class);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public Observable<AdvBean> fetch() {
        throw new AbstractMethodError();
    }
}
