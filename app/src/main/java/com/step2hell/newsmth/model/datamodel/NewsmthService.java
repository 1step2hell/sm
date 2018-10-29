package com.step2hell.newsmth.model.datamodel;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;


public interface NewsmthService {
    @GET("/")
    Single<ResponseBody> request();
}
