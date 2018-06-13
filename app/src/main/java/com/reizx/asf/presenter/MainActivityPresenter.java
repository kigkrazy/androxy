package com.reizx.asf.presenter;

import com.reizx.asf.contract.MainActivityContract;
import com.reizx.asf.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class MainActivityPresenter extends BasePresenterImpl<MainActivityContract.View> implements MainActivityContract.Presenter {
    @Inject
    public MainActivityPresenter() {
    }

    @Override
    public void checkVersion(String currentVersion) {

    }
}
