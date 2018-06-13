package com.reizx.asf.presenter.common;

import com.reizx.asf.component.RxBus;
import com.reizx.asf.view.common.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BasePresenterImpl<T extends BaseView> implements IBasePresenter<T> {
    protected T view;
    // CompositeDisposable是Disposable的容器，用来快速解除订阅，管理多个Disposable的生命周期
    protected CompositeDisposable compositeDisposable;

    /**
     * 解除订阅
     */
    protected void unSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    /**
     * 添加订阅
     *
     * @param subscription
     */
    protected void addSubscribe(Disposable subscription) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    /**
     * 增加订阅
     *
     * @param eventType
     * @param act
     * @param <U>
     */
    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(RxBus.getInstance().toDefaultFlowable(eventType, act));
    }

    @Override
    public void attachView(T view) {
        this.view = view;
        registerEvent();//注册事件
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscribe();
    }

    /**
     * 注册事件
     */
    public void registerEvent(){

    }
}
