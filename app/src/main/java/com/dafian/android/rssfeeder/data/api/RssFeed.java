package com.dafian.android.rssfeeder.data.api;

import java.util.List;

/**
 * @author Dafian on 10/6/17
 */

public class RssFeed {

    private List<RssItem> items;

    public List<RssItem> getItems() {
        return items;
    }

    public void setItems(List<RssItem> items) {
        this.items = items;
    }
}
