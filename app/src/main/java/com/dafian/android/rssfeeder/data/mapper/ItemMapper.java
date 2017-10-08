package com.dafian.android.rssfeeder.data.mapper;

import com.dafian.android.rssfeeder.data.api.RssItem;
import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.data.local.ItemModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dafian on 10/6/17
 */

public class ItemMapper {

    public static List<ItemEntity> toEntity(List<ItemModel> itemModels) {

        List<ItemEntity> itemEntities = new ArrayList<>();

        for (ItemModel model : itemModels) {

            ItemEntity entity = new ItemEntity(
                    model.getTitle(),
                    model.getDescription(),
                    model.getCategory(),
                    model.getImage(),
                    model.getLink(),
                    model.getPublishDate());

            itemEntities.add(entity);
        }

        return itemEntities;
    }

    public static List<ItemModel> toModel(List<RssItem> rssItems) {

        List<ItemModel> itemModels = new ArrayList<>();

        for (RssItem item : rssItems) {

            ItemModel model = new ItemModel();
            model.setTitle(item.getTitle().replace("&#39;", "'").replace("&#039;", "'"));
            model.setDescription(item.getDescription());
            model.setCategory(item.getCategory());
            model.setImage(item.getImage());
            model.setPublishDate(item.getPublishDate());
            model.setLink(item.getLink());

            itemModels.add(model);
        }

        return itemModels;
    }
}
