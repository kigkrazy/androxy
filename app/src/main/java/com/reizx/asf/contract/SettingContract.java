package com.reizx.asf.contract;

import com.reizx.asf.presenter.common.IBasePresenter;
import com.reizx.asf.view.common.BaseView;

public class SettingContract {
    public interface View extends BaseView {
        void showIpStatus(String msg);
    }

    public interface  Presenter extends IBasePresenter<View> {
    }
}
