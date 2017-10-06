package com.dafian.android.rssfeeder.data;

import com.dafian.android.rssfeeder.data.api.RssService;
import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.data.local.DatabaseHelper;
import com.dafian.android.rssfeeder.data.mapper.ItemMapper;
import com.dafian.android.rssfeeder.data.local.ItemModel;
import com.dafian.android.rssfeeder.data.api.RssFeed;
import com.dafian.android.rssfeeder.data.api.RssItem;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * @author Dafian on 10/6/17
 */

public class DataManager {

    private RssService rssService;
    private DatabaseHelper dbHelper;

    public DataManager(RssService rssService, DatabaseHelper dbHelper) {
        this.rssService = rssService;
        this.dbHelper = dbHelper;
    }

    public Observable<List<ItemEntity>> getRss(String url) {

        if (dbHelper.isItemExpired()) {
            return rssService.getRss(url)
                    .flatMap(new Func1<RssFeed, Observable<List<RssItem>>>() {
                        @Override
                        public Observable<List<RssItem>> call(RssFeed rssFeed) {
                            dbHelper.addItem(rssFeed.getItems());
                            return Observable.just(rssFeed.getItems());
                        }
                    })
                    .flatMap(new Func1<List<RssItem>, Observable<List<ItemEntity>>>() {
                        @Override
                        public Observable<List<ItemEntity>> call(List<RssItem> rssItems) {
                            List<ItemModel> itemModels = ItemMapper.toModel(rssItems);
                            return Observable.just(ItemMapper.toEntity(itemModels));
                        }
                    });
        } else {
            return Observable.just(dbHelper.getItemAll());
        }
    }
}
