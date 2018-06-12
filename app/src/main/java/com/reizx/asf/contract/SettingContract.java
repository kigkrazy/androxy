package com.reizx.asf.contract;

import com.reizx.asf.presenter.common.IBasePresenter;
import com.reizx.asf.view.common.BaseView;

public class SettingContract {
    public interface View extends BaseView {

    }

    public interface  Presenter extends IBasePresenter<View> {
    }
}
