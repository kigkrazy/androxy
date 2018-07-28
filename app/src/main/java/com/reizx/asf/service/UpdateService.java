package com.reizx.asf.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import javax.inject.Inject;

public class UpdateService extends Service {
    @Inject



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
