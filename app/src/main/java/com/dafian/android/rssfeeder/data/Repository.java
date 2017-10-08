package com.dafian.android.rssfeeder.data;

import java.util.List;

/**
 * @author Dafian on 10/6/17
 */

public interface Repository<T> {

    static final long EXPIRATION_TIME = 60 * 60 * 1000;

    void add(T item);

    void add(List<T> items);

    List<T> getAll();

    boolean isExpired();

    void remove(T item);

    void removeAll();

    void update(T item);
}
