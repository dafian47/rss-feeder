package com.dafian.android.rssfeeder.data.entity;

/**
 * @author Dafian on 10/6/17
 */

public class ItemEntity {

    private String title;

    private String description;

    private String category;

    private String image;

    private String link;

    private String publishDate;

    public ItemEntity(
            String title, String description, String category, String image,
            String link, String publishDate) {

        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
        this.link = link;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
