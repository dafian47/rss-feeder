package com.dafian.android.rssfeeder.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Dafian on 10/6/17
 */

public final class RssConverterFactory extends Converter.Factory {

    /**
     * Creates an instance
     *
     * @return instance
     */
    public static RssConverterFactory create() {
        return new RssConverterFactory();
    }

    /**
     * Constructor
     */
    private RssConverterFactory() {
        super();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(
            Type type, Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {
        return new RssResponseBodyConverter<>();
    }

    @Override
    public Converter<?, String> stringConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
