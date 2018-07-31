package com.reizx.asf.di.component;

import com.reizx.asf.app.App;
import com.reizx.asf.di.module.AppModule;
import com.reizx.asf.di.module.HttpModule;
import com.reizx.asf.model.DataManager;

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
