package com.reizx.asf.presenter;

import android.content.Context;
import android.content.Intent;

import com.reizx.asf.constant.Constants;
import com.reizx.asf.contract.HomeConstract;
import com.reizx.asf.service.ForegroundService;

import javax.inject.Inject;

public class HomePresenter implements HomeConstract.Presenter{
    HomeConstract.View view;

    @Inject
    public HomePresenter() {
    }


    /**
     * 启动ZK服务
     * @param context app的context
     */
    @Override
    public void startZkService(Context context) {
        Intent intentZkService  = new Intent(context, ForegroundService.class);
        intentZkService.setAction(Constants.FORGROUND_SERVICE_ACTION);
        context.startService(intentZkService);
    }

    @Override
    public void stopZkService(Context context) {
        Intent intentZkService  = new Intent(context, ForegroundService.class);
        intentZkService.setAction(Constants.FORGROUND_SERVICE_ACTION);
        context.stopService(intentZkService);
    }

    @Override
    public void bindZkService() {

    }

    @Override
    public void callHelloZkService() {

    }

    @Override
    public void attachView(HomeConstract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
