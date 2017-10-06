package com.dafian.android.rssfeeder.ui.view;

import com.dafian.android.rssfeeder.data.entity.ItemEntity;
import com.dafian.android.rssfeeder.ui.BaseView;

import java.util.List;

/**
 * @author Dafian on 10/6/17
 */

public interface MainView extends BaseView {

    void showRssItem(List<ItemEntity> items);

    void showEmpty();
}
