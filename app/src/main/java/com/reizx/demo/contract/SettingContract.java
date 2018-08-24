package com.reizx.demo.contract;

import com.reizx.demo.presenter.common.IBasePresenter;
import com.reizx.demo.view.common.BaseView;

public class SettingContract {
    public interface View extends BaseView {
        void showIpStatus(String msg);
    }

    public interface  Presenter extends IBasePresenter<View> {
    }
}
