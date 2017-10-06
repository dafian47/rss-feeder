package com.dafian.android.rssfeeder.data.repository;

import com.dafian.android.rssfeeder.data.Repository;
import com.dafian.android.rssfeeder.data.local.ItemModel;
import com.dafian.android.rssfeeder.util.Helper;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author Dafian on 10/6/17
 */

public class ItemRepository implements Repository<ItemModel> {

    @Override
    public void add(ItemModel item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(item));
        realm.close();
    }

    @Override
    public void add(List<ItemModel> items) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insert(items));
        realm.close();
    }

    @Override
    public void update(ItemModel item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(item));
        realm.close();
    }

    @Override
    public void remove(ItemModel item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> item.deleteFromRealm());
        realm.close();
    }

    @Override
    public void removeAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.delete(ItemModel.class));
        realm.close();
    }

    @Override
    public boolean isExpired() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<ItemModel> items = realm.where(ItemModel.class).findAllAsync();
        if (items.size() == 0) {
            realm.close();
            return true;
        }

        Date currentTime = new Date(System.currentTimeMillis());
        Date lastUpdate = Helper.getDateFromRssItem(items.get(0).getPublishDate());

        boolean isExpired = currentTime.getTime() - lastUpdate.getTime() > EXPIRATION_TIME;

        if (isExpired) {
            realm.close();
            removeAll();
        }

        realm.close();
        return isExpired;
    }

    @Override
    public List<ItemModel> getAll() {
        Realm realm = Realm.getDefaultInstance();
        List<ItemModel> itemModels = realm.where(ItemModel.class).findAllAsync();
        itemModels = realm.copyFromRealm(itemModels);
        realm.close();
        return itemModels;
    }
}
