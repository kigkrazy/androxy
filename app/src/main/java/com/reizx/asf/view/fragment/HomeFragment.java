package com.reizx.asf.view.fragment;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.asf.R;
import com.reizx.asf.contract.HomeConstract;
import com.reizx.asf.presenter.HomePresenter;
import com.reizx.asf.view.common.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeConstract.View{
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @OnClick(R.id.btn_app_start_service)
    public void startZkService(){
        presenter.startZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_stop_service)
    public void stopZkService(){
        presenter.stopZkService(baseActivity);
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        initTopBar();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar(){
        mTopBar.setTitle("主页");
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }
}
