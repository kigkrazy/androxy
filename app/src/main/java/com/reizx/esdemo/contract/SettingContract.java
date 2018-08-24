package com.reizx.esdemo.contract;

import com.reizx.esdemo.presenter.common.IBasePresenter;
import com.reizx.esdemo.view.common.BaseView;

public class SettingContract {
    public interface View extends BaseView {
        void showIpStatus(String msg);
    }

    public interface  Presenter extends IBasePresenter<View> {
    }
}
