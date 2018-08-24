package com.reizx.esdemo.contract;

import com.reizx.esdemo.presenter.common.IBasePresenter;
import com.reizx.esdemo.view.common.BaseView;

public class MainContract {
    public interface View extends BaseView {

    }

    public interface Presenter extends IBasePresenter<View> {

    }
}
