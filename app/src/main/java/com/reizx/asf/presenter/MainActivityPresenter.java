package com.reizx.asf.presenter;

import com.reizx.asf.bean.event.ChangeMainFragmentEvent;
import com.reizx.asf.component.RxBus;
import com.reizx.asf.contract.MainActivityContract;
import com.reizx.asf.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class MainActivityPresenter extends BasePresenterImpl<MainActivityContract.View> implements MainActivityContract.Presenter {
    @Inject
    public MainActivityPresenter() {
    }

    @Override
    public void checkVersion(String currentVersion) {

    }

    @Override
    public void registerEvent() {
        super.registerEvent();

        //注册启动fragment事件
        addSubscribe(RxBus.getInstance().toFlowable(ChangeMainFragmentEvent.class).subscribe(new Consumer<ChangeMainFragmentEvent>() {
            @Override
            public void accept(ChangeMainFragmentEvent changeMainFragmentEvent) throws Exception {
                view.startFragment(changeMainFragmentEvent.getFragment());
            }
        }));
    }
}
