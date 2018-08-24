package com.reizx.esdemo.presenter;

import com.reizx.esdemo.contract.MainContract;
import com.reizx.esdemo.model.DataManager;
import com.reizx.esdemo.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter(DataManager dm) {
        super(dm);
    }
}
