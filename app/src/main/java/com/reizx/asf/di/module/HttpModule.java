package com.reizx.asf.di.module;

import com.reizx.asf.model.retrofit.service.TaskService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class HttpModule {
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    TaskService provideTaskService(Retrofit.Builder builder){
        return null;
    }


    /**
     * 创建Retrofit
     * @param builder
     * @param client
     * @param url
     * @return
     */
    private <T> T createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return null;
//        return builder
//                .baseUrl(url)
//                .client(client)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
    }
}
