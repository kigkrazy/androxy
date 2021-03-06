package com.reizx.androxy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.reizx.androxy.activity.ProxyActivity;
import com.reizx.androxy.core.LocalVpnService;
import com.reizx.androxy.util.AxyLog;

public class AndroxyHelper {
    public static final int START_VPN_SERVICE_REQUEST_CODE = 1985;
    public static String proxyUrl;
    public static Context context;

    static {
        AxyLog.initLog("androxy-tag");
    }
    /**
     * 启动
     *
     * @param context  当前activity
     * @param proxyUrl 代理url
     * 代理url格式有三种 ：
     *       ss://method:password@host:port
     *       ss://base64encode(method:password@host:port)
     *
     *       http://(username:passsword)@host:port
     */
    public static void startVpnService(Context context, String proxyUrl) throws Exception {
        if (!isValidUrl(proxyUrl)) {
            throw new Exception("the proxy url is valid.");
        }
        ProxyActivity.start(context, proxyUrl);
        //new AppProxyManager(context);
//        Intent intent = new Intent(context, ProxyActivity.class);
//        context.startActivity(intent);
//        new AppProxyManager(context);
//        ProxyConfig.Instance.globalMode = true;
//        AndroxyHelper.context = context;
//        AndroxyHelper.proxyUrl = proxyUrl;
//        LocalVpnService.ProxyUrl = proxyUrl;
//        context.startService(new Intent(context, LocalVpnService.class));
    }


    /**
     * 停止代理
     */
    public static void stopVpnService() {
        LocalVpnService.IsRunning = false;
    }


    /**
     * 检验配置url是否正确
     *
     * @param url
     * @return
     */
    private static boolean isValidUrl(String url) {
        try {
            if (url == null || url.isEmpty())
                return false;

            if (url.startsWith("ss://")) {//file path
                return true;
            } else { //url
                Uri uri = Uri.parse(url);
                if (!"http".equals(uri.getScheme()) && !"https".equals(uri.getScheme()))
                    return false;
                if (uri.getHost() == null)
                    return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
