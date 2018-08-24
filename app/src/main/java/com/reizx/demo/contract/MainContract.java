package com.reizx.demo.contract;

import com.reizx.demo.presenter.common.IBasePresenter;
import com.reizx.demo.view.common.BaseView;

public class MainContract {
    public interface View extends BaseView {

    }

    public interface Presenter extends IBasePresenter<View> {

    }
}
