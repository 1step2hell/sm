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
import retrofit2.http.GET;


public class DataModel implements IDataModel {

    private final static String URL_NEWSMTH = "http://www.newsmth.net/";
    private final static String REG_PREIMG = "preimg=\\[(.*?)\\]";

    @Override
    public Observable<AdvBean> getAdv() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_NEWSMTH)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        NewsmthInterface service = retrofit.create(NewsmthInterface.class);
        Observable<AdvBean> observable = service.requestNewsmth()
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, AdvBean>() {
                    @Override
                    public AdvBean apply(@NonNull ResponseBody responseBody) throws Exception {
                        String jsonStr = HtmlUtil.getSubSimple(responseBody.string(), REG_PREIMG);
                        AdvBean bean = new Gson().fromJson(jsonStr, AdvBean.class);
                        return bean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    interface NewsmthInterface {
        @GET("/")
        Observable<ResponseBody> requestNewsmth();
    }
}
