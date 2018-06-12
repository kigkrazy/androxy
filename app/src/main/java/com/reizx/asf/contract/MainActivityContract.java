package com.reizx.asf.contract;

import com.reizx.asf.presenter.common.IBasePresenter;
import com.reizx.asf.view.common.BaseView;

public class MainActivityContract {
    public interface View extends BaseView {

        //void showUpdateDialog(String versionContent);

        //void startDownloadService();
    }

    public interface  Presenter extends IBasePresenter<View> {

        void checkVersion(String currentVersion);
    }
}
