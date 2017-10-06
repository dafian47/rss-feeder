package com.dafian.android.rssfeeder.data.api;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @author Dafian on 10/6/17
 */

public interface RssService {

    @GET
    Observable<RssFeed> getRss(@Url String url);
}
