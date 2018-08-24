package com.reizx.demo.di.component;

import com.reizx.demo.di.module.ServiceModule;
import com.reizx.demo.di.scope.ServiceScope;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ServiceScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
