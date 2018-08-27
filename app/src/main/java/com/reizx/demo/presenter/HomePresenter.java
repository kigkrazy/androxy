package com.reizx.demo.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.reizx.demo.bean.event.TipEvent;
import com.reizx.demo.component.RxBus;
import com.reizx.demo.constant.Constants;
import com.reizx.demo.contract.HomeConstract;
import com.reizx.demo.bean.event.IpStatusEvent;
import com.reizx.demo.model.DataManager;
import com.reizx.demo.model.retrofit.api.IpApi;
import com.reizx.demo.presenter.common.BasePresenterImpl;
import com.reizx.demo.service.ForegroundService;
import com.reizx.demo.util.AsfLog;
import com.reizx.demo.util.RxUtil;
import com.white.easysp.EasySP;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class HomePresenter extends BasePresenterImpl<HomeConstract.View> implements HomeConstract.Presenter{
    @Inject
    public HomePresenter(DataManager dm) {
        super(dm);
    }

    /**
     * 启动ZK服务
     * @param context app的context
     */
    @Override
    public void startProxyService(Context context) {
        Intent intentZkService  = new Intent(context, ForegroundService.class);
        intentZkService.setAction(Constants.FORGROUND_SERVICE_ACTION);
        context.startService(intentZkService);
    }

    @Override
    public EasySP getEastSP() {
        return dm.getEasySP();
    }
}
