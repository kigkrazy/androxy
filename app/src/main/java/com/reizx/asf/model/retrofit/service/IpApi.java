package com.reizx.asf.model.retrofit.service;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IpApi {
    String HOST = "http://2018.ip138.com/";

    /**
     * 获取IP信息
     * @return
     */
    @GET("/ic.asp")
    Flowable<ResponseBody> getCurrentIp();

}
