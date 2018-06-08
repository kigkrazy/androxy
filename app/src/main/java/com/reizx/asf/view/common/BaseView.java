package com.reizx.asf.view.common;

public interface BaseView {

    /**
     * 返回界面错误信息，并处理
     * @param msg
     */
    void showErrorMsg(String msg);

    //=======  State  =======

    /**
     * 界面显示出错时候处理
     * 例如：
     * 1. 界面加载失败
     * 2. 网络请求失败时候处理
     */
    void stateError();


    /**
     * 界面为空，时候处理
     */
    void stateEmpty();

    /**
     * 界面正在加载
     */
    void stateLoading();

    /**
     * 界面状态执行
     */
    void stateMain();
}
