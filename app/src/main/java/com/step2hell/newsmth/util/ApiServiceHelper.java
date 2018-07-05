package com.step2hell.newsmth.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings({"unchecked","ImmutableEnumChecker"})
public enum ApiServiceHelper {

    INSTANCE {

        private HashMap<String, Retrofit> retrofitMap = new HashMap<>();
        private SoftReference<HashMap<String, Object>> serviceMapRefs = new SoftReference<>(new HashMap<String, Object>());

        @Override
        public <T> T createService(String baseUrl, Class<T> service) {
            T t = (T) serviceMapRefs.get().get(baseUrl.concat(service.getName()));
            if (t == null) {
                t = buildRetrofitWithUrl(baseUrl).create(service);
                serviceMapRefs.get().put(baseUrl.concat(service.getName()), t);
            }
            return t;
        }

        private Retrofit buildRetrofitWithUrl(String baseUrl) {
            Retrofit retrofit = retrofitMap.get(baseUrl);
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .client(HttpClient.INSTANCE.getClient())
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create()) // Todo: custom converter
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                retrofitMap.put(baseUrl, retrofit);
            }
            return retrofit;
        }
    };

    public <T> T createService(String baseUrl, Class<T> service) {
        throw new AbstractMethodError();
    }

}
