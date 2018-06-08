package com.reizx.asf.di.component;

import android.app.Activity;

import com.reizx.asf.di.module.FragmentModule;
import com.reizx.asf.di.scope.FragmentScope;
import com.reizx.asf.view.fragment.HomeFragment;
import com.reizx.asf.view.fragment.MainFragment;
import com.reizx.asf.view.fragment.SettingFragment;

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
