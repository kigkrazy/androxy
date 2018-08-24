package com.reizx.esdemo.di.component;

import android.app.Activity;

import com.reizx.esdemo.di.module.ActivityModule;
import com.reizx.esdemo.di.scope.ActivityScope;
import com.reizx.esdemo.view.MainActivity;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);
}
