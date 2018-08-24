package com.reizx.demo.presenter;

import com.reizx.demo.bean.event.IpStatusEvent;
import com.reizx.demo.component.RxBus;
import com.reizx.demo.contract.SettingContract;
import com.reizx.demo.model.DataManager;
import com.reizx.demo.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class SettingPresenter extends BasePresenterImpl<SettingContract.View> implements SettingContract.Presenter{
    @Inject
    public SettingPresenter(DataManager dm) {
        super(dm);
    }

    /**
     * 注册事件
     */
    @Override
    public void registerEvent() {
        super.registerEvent();
        addSubscribe(RxBus.getInstance().toFlowable(IpStatusEvent.class)
                        .subscribe(new Consumer<IpStatusEvent>() {
                            @Override
                            public void accept(IpStatusEvent ipStatusEvent) throws Exception {
                                String msg = ipStatusEvent.getTimestamp() + "\n" + ipStatusEvent.getIpistatus();
                                view.showIpStatus(msg);
                            }
                        }));

    }
}