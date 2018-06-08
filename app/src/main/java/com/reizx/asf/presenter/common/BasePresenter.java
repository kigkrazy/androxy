package com.reizx.asf.presenter.common;

import com.reizx.asf.view.common.BaseView;

/**
 * 基础的presenter类
 * @param <T>
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
