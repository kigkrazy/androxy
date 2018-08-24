package com.reizx.demo.presenter.common;

import com.reizx.demo.view.common.BaseView;

/**
 * 基础的presenter类
 * @param <T>
 */
public interface IBasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
