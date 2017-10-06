package com.dafian.android.rssfeeder.presenter;

import com.dafian.android.rssfeeder.ui.BaseView;

/**
 * @author Dafian on 10/6/17
 */

public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
