package com.step2hell.newsmth.model.datamodel;

import com.step2hell.newsmth.model.bean.AdvBean;

import io.reactivex.Observable;


public interface IDataModel {

    Observable<AdvBean> getAdv();

}
