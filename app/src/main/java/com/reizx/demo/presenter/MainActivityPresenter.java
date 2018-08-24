package com.reizx.demo.presenter;

import com.reizx.demo.bean.event.ChangeMainFragmentEvent;
import com.reizx.demo.component.RxBus;
import com.reizx.demo.contract.MainActivityContract;
import com.reizx.demo.model.DataManager;
import com.reizx.demo.presenter.common.BasePresenterImpl;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class MainActivityPresenter extends BasePresenterImpl<MainActivityContract.View> implements MainActivityContract.Presenter {
    @Inject
    public MainActivityPresenter(DataManager dm) {
        super(dm);
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
