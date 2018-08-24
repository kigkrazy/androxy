package com.reizx.esdemo.di.component;

import com.reizx.esdemo.di.module.ServiceModule;
import com.reizx.esdemo.di.scope.ServiceScope;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ServiceScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
