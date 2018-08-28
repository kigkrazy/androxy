package com.reizx.androxy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.reizx.androxy.AndroxyHelper;
import com.reizx.androxy.core.AppProxyManager;
import com.reizx.androxy.core.LocalVpnService;
import com.reizx.androxy.core.ProxyConfig;

import static com.reizx.androxy.AndroxyHelper.START_VPN_SERVICE_REQUEST_CODE;

public class ProxyActivity extends Activity {
    public static String proxyUrl;
    public static Context context;

    public static void start(final Context context, String proxyUrl) {
        ProxyActivity.proxyUrl = proxyUrl;
        ProxyActivity.context = context;

        Intent starter = new Intent(context, ProxyActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        //启动代理
        new AppProxyManager(context);
        Intent intent = LocalVpnService.prepare(this);
        if (intent != null) {
            this.startActivityForResult(intent, START_VPN_SERVICE_REQUEST_CODE);
            return;
        }

        ProxyConfig.Instance.globalMode = true;
        LocalVpnService.ProxyUrl = ProxyActivity.proxyUrl;
        this.startService(new Intent(this, LocalVpnService.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == START_VPN_SERVICE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    AndroxyHelper.startVpnService(this, ProxyActivity.proxyUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
