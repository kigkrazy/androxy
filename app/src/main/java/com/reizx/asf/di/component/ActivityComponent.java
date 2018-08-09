package com.reizx.asf.di.component;

import android.app.Activity;

import com.reizx.asf.di.module.ActivityModule;
import com.reizx.asf.di.scope.ActivityScope;
import com.reizx.asf.view.MainActivity;

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
