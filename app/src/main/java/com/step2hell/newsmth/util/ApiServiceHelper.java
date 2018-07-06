package com.step2hell.newsmth.util;

import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * https://www.jianshu.com/p/308f3c54abdd
 */
@SuppressWarnings({"unchecked", "ImmutableEnumChecker"})
public enum ApiServiceHelper {

    INSTANCE {

        private HashMap<String, Retrofit> retrofitMap = new HashMap<>();
        private HashMap<String, Object> serviceMap = new HashMap<>();

        @Override
        public <T> T createService(String baseUrl, Class<T> service) {
            T t = (T) serviceMap.get(baseUrl.concat(service.getName()));
            if (t == null) {
                t = buildRetrofit(baseUrl).create(service);
                serviceMap.put(baseUrl.concat(service.getName()), t);
            }
            return t;
        }

        private Retrofit buildRetrofit(String baseUrl) {
            Retrofit retrofit = retrofitMap.get(baseUrl);
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .client(HttpClient.INSTANCE.getClient())
                        .baseUrl(baseUrl)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(
                                GsonConverterFactory.create(
                                        new GsonBuilder()
                                                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                                                .setPrettyPrinting()
                                                .create() // custom Gson
                                )
                        ) // Todo: custom converter
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
