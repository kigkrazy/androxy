package com.reizx.asf.view.fragment;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.asf.R;
import com.reizx.asf.broadcast.BootBroadcast;
import com.reizx.asf.contract.SettingContract;
import com.reizx.asf.presenter.SettingPresenter;
import com.reizx.asf.util.AsfMgrLog;
import com.reizx.asf.view.common.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_setting_page_show_ip_des)
    TextView tvIpStatus;

    @OnClick(R.id.btn_setting_page_test)
    public void clickTest(){
        AsfMgrLog.d("click setting page test");
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_setting;
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

    public void initTopBar() {
        mTopBar.setTitle("设置");
    }

    @Override
    public void showIpStatus(String msg) {
        tvIpStatus.setText(msg);
    }
}
