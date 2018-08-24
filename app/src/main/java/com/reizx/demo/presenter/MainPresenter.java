package com.reizx.demo.presenter;

import com.reizx.demo.contract.MainContract;
import com.reizx.demo.model.DataManager;
import com.reizx.demo.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter(DataManager dm) {
        super(dm);
    }
}
