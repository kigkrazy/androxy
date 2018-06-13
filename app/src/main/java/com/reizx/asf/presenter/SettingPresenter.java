package com.reizx.asf.presenter;

import com.reizx.asf.contract.SettingContract;
import com.reizx.asf.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

public class SettingPresenter extends BasePresenterImpl<SettingContract.View> implements SettingContract.Presenter{
    @Inject
    public SettingPresenter() {
    }
}