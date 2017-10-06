package com.dafian.android.rssfeeder.data.local;

import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.data.mapper.ItemMapper;
import com.dafian.android.rssfeeder.data.repository.ItemRepository;
import com.dafian.android.rssfeeder.data.api.RssItem;

import java.util.List;

/**
 * @author Dafian on 10/6/17
 */

public class DatabaseHelper {

    private ItemRepository itemRepository;

    public DatabaseHelper(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(List<RssItem> rssItems) {
        itemRepository.add(ItemMapper.toModel(rssItems));
    }

    public boolean isItemExpired() {
        return itemRepository.isExpired();
    }

    public List<ItemEntity> getItemAll() {
        return ItemMapper.toEntity(itemRepository.getAll());
    }
}
