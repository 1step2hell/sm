package com.step2hell.newsmth.model.viewmodel;

import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.model.datamodel.DataModel;

import io.reactivex.Observable;


public class ViewModel {
    private DataModel mDataModel;

    public ViewModel(DataModel mDataModel) {
        this.mDataModel = mDataModel;
    }

    public Observable<AdvBean> fetchData() {
        return mDataModel.fetch();
    }
}
