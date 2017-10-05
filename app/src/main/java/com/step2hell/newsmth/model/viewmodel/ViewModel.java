package com.step2hell.newsmth.model.viewmodel;

import com.step2hell.newsmth.model.bean.AdvBean;
import com.step2hell.newsmth.model.datamodel.DataModel;

import io.reactivex.Observable;


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
}
