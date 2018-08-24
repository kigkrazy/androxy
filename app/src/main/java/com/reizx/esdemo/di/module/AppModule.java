package com.reizx.esdemo.di.module;

import com.reizx.esdemo.app.App;
import com.reizx.esdemo.model.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by kigkrazy on 18-5-12.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Singleton
    @Provides
    DataManager provideDataManager(App app, Retrofit.Builder builder, OkHttpClient client) {
        return new DataManager(app, builder, client);
    }
}
