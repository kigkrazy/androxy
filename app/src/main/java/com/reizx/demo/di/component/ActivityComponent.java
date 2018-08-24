package com.reizx.demo.di.component;

import android.app.Activity;

import com.reizx.demo.di.module.ActivityModule;
import com.reizx.demo.di.scope.ActivityScope;
import com.reizx.demo.view.MainActivity;

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
