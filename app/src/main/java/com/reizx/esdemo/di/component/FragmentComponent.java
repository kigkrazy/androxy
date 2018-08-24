package com.reizx.esdemo.di.component;

import android.app.Activity;

import com.reizx.esdemo.di.module.FragmentModule;
import com.reizx.esdemo.di.scope.FragmentScope;
import com.reizx.esdemo.view.fragment.HomeFragment;
import com.reizx.esdemo.view.fragment.MainFragment;
import com.reizx.esdemo.view.fragment.SettingFragment;

import dagger.Component;

/**
 * Created by kigkrazy on 18-5-12.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void Inject(HomeFragment homeFragment);

    void Inject(SettingFragment settingFragment);

    void Inject(MainFragment settingFragment);
}
