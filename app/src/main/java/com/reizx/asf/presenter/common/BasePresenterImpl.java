package com.reizx.asf.presenter.common;

import com.reizx.asf.view.common.BaseView;

public class BasePresenterImpl <T extends BaseView> implements IBasePresenter<T> {
    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
