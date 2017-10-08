package com.dafian.android.rssfeeder.data;

import com.dafian.android.rssfeeder.data.api.RssService;
import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.data.local.DatabaseHelper;
import com.dafian.android.rssfeeder.data.local.ItemModel;
import com.dafian.android.rssfeeder.data.mapper.ItemMapper;
import java.util.List;
import rx.Observable;

/**
 * @author Dafian on 10/6/17
 */

public class DataManager {

    private DatabaseHelper dbHelper;

    private RssService rssService;

    public DataManager(RssService rssService, DatabaseHelper dbHelper) {
        this.rssService = rssService;
        this.dbHelper = dbHelper;
    }

    public Observable<List<ItemEntity>> getRss(String url) {

        if (dbHelper.isItemExpired()) {
            return rssService.getRss(url)
                    .flatMap(rssFeed -> {
                        dbHelper.addItem(rssFeed.getItems());
                        return Observable.just(rssFeed.getItems());
                    })
                    .flatMap(rssItems -> {
                        List<ItemModel> itemModels = ItemMapper.toModel(rssItems);
                        return Observable.just(ItemMapper.toEntity(itemModels));
                    });
        } else {
            return Observable.just(dbHelper.getItemAll());
        }
    }
}
