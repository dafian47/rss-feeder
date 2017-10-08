package com.dafian.android.rssfeeder.data.local;

import io.realm.RealmObject;

/**
 * @author Dafian on 10/6/17
 */

public class ItemModel extends RealmObject {

    private String category;

    private String description;

    private String image;

    private String link;

    private String publishDate;

    private String title;

    public ItemModel() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
