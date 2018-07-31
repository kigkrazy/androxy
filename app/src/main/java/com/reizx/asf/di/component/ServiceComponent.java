package com.reizx.asf.di.component;

import com.reizx.asf.di.module.ServiceModule;
import com.reizx.asf.di.scope.ServiceScope;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ServiceScope
@Component(dependencies = AppComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
}
