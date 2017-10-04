package com.step2hell.newsmth.model.datamodel;

import com.google.gson.Gson;
import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.util.HtmlUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


public class DataModel implements IDataModel {

    private final static String URL_NEWSMTH = "http://www.newsmth.net/";
    private final static String REG_PREIMG = "preimg=\\[(.*?)\\]";

    @Override
    public Observable<AdvBean> getAdv() {

        Observable<AdvBean> observable = Observable.create(new ObservableOnSubscribe<AdvBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<AdvBean> e) throws Exception {
                try {
                    String ioStr = HtmlUtil.getHTML(URL_NEWSMTH);
                    String jsonStr = HtmlUtil.getSubSimple(ioStr, REG_PREIMG);
                    AdvBean bean = new Gson().fromJson(jsonStr, AdvBean.class);
                    e.onNext(bean);
                    e.onComplete();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    e.onComplete();
                }
            }
        }).subscribeOn(Schedulers.newThread());
        return observable;

//        Observable<AdvBean> observable = new Observable<AdvBean>() {
//            @Override
//            protected void subscribeActual(Observer<? super AdvBean> observer) {
//                try {
//                    String ioStr = HtmlUtil.getHTML(URL_NEWSMTH);
//                    String jsonStr = HtmlUtil.getSubSimple(ioStr,REG_PREIMG);
//                    AdvBean bean = new Gson().fromJson(jsonStr,AdvBean.class);
//                    observer.onNext(bean);
//                    observer.onComplete();
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                    observer.onComplete();
//                }
//            }
//        }.subscribeOn(Schedulers.newThread());
//        return observable;
    }
}
