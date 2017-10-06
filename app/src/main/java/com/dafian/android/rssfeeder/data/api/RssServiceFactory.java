package com.dafian.android.rssfeeder.data.api;

import android.support.annotation.NonNull;

import com.dafian.android.rssfeeder.BuildConfig;
import com.dafian.android.rssfeeder.config.ApiConstants;
import com.dafian.android.rssfeeder.util.RssConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author Dafian on 10/6/17
 */

public class RssServiceFactory {

    public static RssService create() {
        OkHttpClient okHttp = makeClientService(makeLoggingInterceptor());
        return makeRssService(okHttp);
    }

    private static RssService makeRssService(OkHttpClient okHttp) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.URL_RSS)
                .client(okHttp)
                .addConverterFactory(RssConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(RssService.class);
    }

    @NonNull
    private static OkHttpClient makeClientService(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder ongoing = chain.request().newBuilder();
                    ongoing.addHeader("Cache-control", "public,max-age=0");
                    ongoing.addHeader("Content-Type", "application/json");
                    ongoing.addHeader("X-API-KEY", "123456");
                    return chain.proceed(ongoing.build());
                })
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @NonNull
    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
    }
}
