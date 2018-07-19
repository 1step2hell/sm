package com.step2hell.newsmth.model.datamodel;

import com.google.gson.Gson;
import com.step2hell.newsmth.model.bean.AdBean;
import com.step2hell.newsmth.util.ApiServiceHelper;
import com.step2hell.newsmth.util.HtmlUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public enum DataModel {
    ADV {
        private final static String URL_NEWSMTH = "http://www.newsmth.net/";
        private final static String REG_PREIMG = "preimg=\\[(.*?)\\]";

        @Override
        public Observable<AdBean> fetch() {
            return ApiServiceHelper.INSTANCE
                    .createService(URL_NEWSMTH, NewsmthService.class)
                    .request()
                    .subscribeOn(Schedulers.newThread())
                    .map(new Function<ResponseBody, AdBean>() {
                        @Override
                        public AdBean apply(@NonNull ResponseBody responseBody) throws Exception {
                            return new Gson().fromJson(HtmlUtil.getSubSample(responseBody.string(), REG_PREIMG), AdBean.class);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public Observable<AdBean> fetch() {
        throw new AbstractMethodError();
    }
}
