package com.reizx.androxy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.reizx.androxy.core.AppProxyManager;
import com.reizx.androxy.core.LocalVpnService;
import com.reizx.androxy.core.ProxyConfig;

public class AndroxyHelper {
    public static final int START_VPN_SERVICE_REQUEST_CODE = 1985;
    public static String proxyUrl;
    public static Context context;

    /**
     * 启动
     * @param context 当前activity
     * @param proxyUrl 代理url
     */
    public static void startVpnService(Context context, String proxyUrl) throws Exception {
        if (!isValidUrl(proxyUrl)){
            throw new Exception("the proxy url is valid.");
        }
        new AppProxyManager(context);
        ProxyConfig.Instance.globalMode = true;
        AndroxyHelper.context = context;
        AndroxyHelper.proxyUrl = proxyUrl;
        LocalVpnService.ProxyUrl = proxyUrl;
        context.startService(new Intent(context, LocalVpnService.class));
    }



    /**
     * 检验配置url是否正确
     *
     * @param url
     * @return
     */
    public static boolean isValidUrl(String url) {
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
