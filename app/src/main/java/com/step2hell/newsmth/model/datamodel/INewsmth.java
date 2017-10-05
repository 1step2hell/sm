package com.step2hell.newsmth.model.datamodel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;


public interface INewsmth {
    @GET("/")
    Observable<ResponseBody> request();
}
