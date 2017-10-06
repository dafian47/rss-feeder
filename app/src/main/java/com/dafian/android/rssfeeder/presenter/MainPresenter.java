package com.dafian.android.rssfeeder.presenter;

import com.dafian.android.rssfeeder.data.DataManager;
import com.dafian.android.rssfeeder.ui.view.MainView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Dafian on 10/6/17
 */

public class MainPresenter extends BasePresenter<MainView> {

    private Subscription subscription;

    private DataManager manager;

    public MainPresenter(DataManager manager) {
        this.manager = manager;
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null) subscription.unsubscribe();
    }

    public void getRss(String url) {

        if (subscription != null) subscription.unsubscribe();

        subscription = manager.getRss(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                    if (isViewAttached()) {
                        if (items.isEmpty()) {
                            getView().showEmpty();
                            return;
                        }
                        getView().showRssItem(items);
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().showError(throwable.getLocalizedMessage());
                    }
                });
    }
}
