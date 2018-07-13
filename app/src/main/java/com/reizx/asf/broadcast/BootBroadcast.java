package com.reizx.asf.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.reizx.asf.util.AsfMgrLog;

/**
 * 开机启动广播
 */
public class BootBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AsfMgrLog.d(BootBroadcast.class.toString(),"receive BootBroadcast");
    }
}
