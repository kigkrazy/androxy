package com.reizx.esdemo.di.component;

import com.reizx.esdemo.app.App;
import com.reizx.esdemo.di.module.AppModule;
import com.reizx.esdemo.di.module.HttpModule;
import com.reizx.esdemo.model.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    DataManager getDataManager();//数据管理实例
}
