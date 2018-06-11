package com.reizx.asf.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import com.reizx.asf.constant.Constants;
import com.reizx.asf.contract.HomeConstract;
import com.reizx.asf.model.retrofit.api.IpApi;
import com.reizx.asf.service.ForegroundService;
import com.reizx.asf.util.AsfMgrLog;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class HomePresenter implements HomeConstract.Presenter{
    HomeConstract.View view;
    IpApi ipApi;

    @Inject
    public HomePresenter(IpApi ipApi) {
        this.ipApi = ipApi;
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

    @SuppressLint("CheckResult")
    @Override
    public void showCurrentIp() {
        //view.setCurrentIp();
        AsfMgrLog.d("showCurrentIp...");
        ipApi.getCurrentIp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   String result = new String(responseBody.bytes(), "GB2312");
                                   view.setCurrentIp(result);
                               }
                           });

    }

    @Override
    public void attachView(HomeConstract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
