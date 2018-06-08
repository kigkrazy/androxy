package com.reizx.asf.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.reizx.asf.R;
import com.reizx.asf.constant.Constants;
import com.reizx.asf.util.AsfMgrLog;

/**
 * Created by kigkrazy on 18-5-10.
 */

public class ForegroundService extends Service {
    public final static String TAG = "ForegroundService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AsfMgrLog.d(TAG, "--------->onStartCommand: ");
        // 在API11之后构建Notification的方式
        //NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext()); //获取一个Notification构造器
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle(Constants.FORGROUND_SERVICE_TITILE) // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText(Constants.FORGROUND_SERVICE_CONTENT_TEXT) // 设置描述
                .setContentIntent(PendingIntent.getBroadcast(this, 1011, new Intent(this, NotificationClickReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT))
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(Constants.FORGROUND_SERVICE_ID, notification);
        //notificationManager.notify(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public static class NotificationClickReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            AsfMgrLog.d(TAG, "NotificationClickReceiver --------->onReceive: stop service");
            context.stopService(new Intent(context, ForegroundService.class));
        }
    }
}
