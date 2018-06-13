package com.reizx.asf.presenter;

import com.reizx.asf.contract.MainContract;
import com.reizx.asf.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
}
