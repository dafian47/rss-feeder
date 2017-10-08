package com.dafian.android.rssfeeder.ui;

import android.support.v4.app.Fragment;
import com.dafian.android.rssfeeder.data.DataManager;
import com.dafian.android.rssfeeder.data.api.RssService;
import com.dafian.android.rssfeeder.data.api.RssServiceFactory;
import com.dafian.android.rssfeeder.data.local.DatabaseHelper;
import com.dafian.android.rssfeeder.data.repository.ItemRepository;

/**
 * @author Dafian on 10/6/17
 */

public abstract class BaseFragment extends Fragment {

    private DatabaseHelper dbHelper;

    private DataManager manager;

    private RssService rssService;

    public DatabaseHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(new ItemRepository());
        }
        return dbHelper;
    }

    public DataManager getManager() {
        if (manager == null) {
            manager = new DataManager(getRssService(), getDbHelper());
        }
        return manager;
    }

    public RssService getRssService() {
        if (rssService == null) {
            rssService = RssServiceFactory.create();
        }
        return rssService;
    }
}
