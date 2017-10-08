package com.dafian.android.rssfeeder.presenter;

import com.dafian.android.rssfeeder.ui.BaseView;

/**
 * @author Dafian on 10/6/17
 */

public class BasePresenter<T extends BaseView> implements Presenter<T> {

    public static class BaseViewNotAttachedException extends RuntimeException {

        public BaseViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    private T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new BaseViewNotAttachedException();
        }
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public T getView() {
        return view;
    }

    public boolean isViewAttached() {
        return view != null;
    }
}
