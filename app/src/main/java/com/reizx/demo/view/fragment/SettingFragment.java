package com.reizx.demo.view.fragment;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.demo.R;
import com.reizx.demo.contract.SettingContract;
import com.reizx.demo.presenter.SettingPresenter;
import com.reizx.demo.util.AsfLog;
import com.reizx.demo.util.RxUtil;
import com.reizx.demo.view.common.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_setting_page_show_ip_des)
    TextView tvIpStatus;
    Disposable ds;

    @SuppressLint("CheckResult")
    @OnClick(R.id.btn_setting_page_test)
    public void clickTest(){
        if (ds != null && !ds.isDisposed()){
            AsfLog.d("dispose the subscribe...");
            ds.dispose();
            ds = null;
            return;
        }

        AsfLog.d("click setting page test");
        ds = Flowable.interval(1,  TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        AsfLog.d("the inter ... " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AsfLog.d("flow err : " + throwable);
                    }
                });
    }

    @OnClick(R.id.btn_setting_page_xlog)
    public void printXlog(){
        AsfLog.d("start exec ...");
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onCreateViewFinish() {
        super.onCreateViewFinish();
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
