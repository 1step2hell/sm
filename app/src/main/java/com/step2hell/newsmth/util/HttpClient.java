package com.step2hell.newsmth.util;

import com.step2hell.newsmth.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public enum HttpClient {

    INSTANCE {
        private final long READ_TIMEOUT = 60_1000;
        private final long WRITE_TIMEOUT = 60_1000;
        private OkHttpClient client;

        @Override
        public OkHttpClient getClient() {
            if (client == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    builder.addInterceptor(httpLoggingInterceptor);
                }
                builder.readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                        .addInterceptor(chain -> {
                            Request request = chain.request();
                            Response response = chain.proceed(request);
                            return response;
                        }); // Todo: cache/interceptor/ssl in client
                client = builder.build();
            }
            return client;
        }
    };

    public OkHttpClient getClient() {
        throw new AbstractMethodError();
    }
}
