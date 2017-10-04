package com.step2hell.newsmth.model.viewmodel;

import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.model.datamodel.IDataModel;

import io.reactivex.Observable;


public class ViewModel {
    private IDataModel mDataModel;

    public ViewModel(IDataModel mDataModel) {
        this.mDataModel = mDataModel;
    }

    public Observable<AdvBean> getAdv() {
        return mDataModel.getAdv();
    }
}
