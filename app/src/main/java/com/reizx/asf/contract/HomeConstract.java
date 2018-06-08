package com.reizx.asf.contract;

import android.content.Context;

import com.reizx.asf.presenter.common.BasePresenter;
import com.reizx.asf.view.common.BaseView;

/**
 * 主页的借口约定类
 */
public class HomeConstract {
    public interface View extends BaseView{

    }

    public interface Presenter extends BasePresenter<View>{
        /**
         * 开始zookeeper的前台服务
         */
        void startZkService(Context context);

        /**
         * 开始zookeeper的前台服务
         */
        void stopZkService(Context context);

        /**
         * 绑定zookeeper的服务（用于测试）
         */
        void bindZkService();

        /**
         * 请求zookeeper的接口（用于测试）
         */
        void callHelloZkService();
    }
}
