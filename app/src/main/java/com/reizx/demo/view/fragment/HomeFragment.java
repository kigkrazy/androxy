package com.reizx.demo.view.fragment;

import android.os.IBinder;
import android.os.RemoteException;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.demo.IAndromedaInf;
import com.reizx.demo.R;
import com.reizx.demo.contract.HomeConstract;
import com.reizx.demo.presenter.HomePresenter;
import com.reizx.demo.util.AsfLog;
import com.reizx.demo.view.common.BaseFragment;

import org.qiyi.video.svg.Andromeda;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeConstract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_app_show_ip_des)
    TextView tvIp;

    @OnClick(R.id.btn_app_start_service)
    public void startZkService() {
        presenter.startZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_stop_service)
    public void stopZkService() {
        presenter.stopZkService(baseActivity);
    }

    @OnClick(R.id.btn_app_request_ip)
    public void requestIp() {
        presenter.showCurrentIp();
    }

    @OnClick(R.id.btn_app_andromeda_call)
    public void andromedaCall() {
        IBinder binder = Andromeda.with(app).getRemoteService(IAndromedaInf.class);
        if (binder == null) {
            return;
        }
        IAndromedaInf andromedaInf = IAndromedaInf.Stub.asInterface(binder);
        if (andromedaInf == null) {
            return;
        }
        try {
            andromedaInf.remoteCall();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * butterknife处理radiobutton
     * @param view
     * @param ischanged
     */
    @OnCheckedChanged({R.id.rg_home_proxy_mode_ss, R.id.rg_home_proxy_mode_http})
    public void OnCheckedChangeListener(CompoundButton view, boolean ischanged) {
        switch (view.getId()) {
            case R.id.rg_home_proxy_mode_ss:
                if (ischanged){//注意：这里一定要有这个判断，只有对应该id的按钮被点击了，ischanged状态发生改变，才会执行下面的内容
                    ToastUtils.showLong("启动ss模式");
                }
                break;
            case R.id.rg_home_proxy_mode_http:
                if (ischanged) {
                    //这里写你的按钮变化状态的UI及相关逻辑
                    ToastUtils.showLong("启动http模式");
                }
                break;
            default:
                break;
        }
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

    public void initTopBar() {
        mTopBar.setTitle("主页");
    }

    @Override
    public void setCurrentIp(String ip) {
        tvIp.setText(ip);
    }
}
