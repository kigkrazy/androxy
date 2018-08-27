package com.reizx.demo.contract;

import android.content.Context;

import com.reizx.demo.presenter.common.IBasePresenter;
import com.reizx.demo.view.common.BaseView;
import com.white.easysp.EasySP;

/**
 * 主页的借口约定类
 */
public class HomeConstract {
    public interface View extends BaseView{
        /**
         * 设置当前IP
         * @param ip
         */
        public void setCurrentIp(String ip);
    }

    public interface Presenter extends IBasePresenter<View> {
        /**
         * 开始zookeeper的前台服务
         */
        void startProxyService(Context context);

        /**
         * 获取sp管理对象
         * @return
         */
        public EasySP getEastSP();
    }
}
