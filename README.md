# androxy
安卓下简单的HTTP代理以及SSsock代理服务调用库。


## 使用
1. 在关联的ACTIVITY中处理vpn请求
```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (requestCode == START_VPN_SERVICE_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    AndroxyHelper.startVpnService(AndroxyHelper.context, AndroxyHelper.proxyUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
```
2. 可以处理代理
```
    @OnClick(R.id.btn_app_start_service)
    public void startProxyService() {
        try{
            Intent intent = LocalVpnService.prepare(baseActivity);
            if (intent != null) {
                baseActivity.startActivityForResult(intent, START_VPN_SERVICE_REQUEST_CODE);
                return;
            }

            String proxyUrl = getProxyUrl();//代理url请参考 *代理信息格式*
            AndroxyHelper.startVpnService(baseActivity, proxyUrl);
        }catch (Exception e){
            AxyLog.d("启动代理错误");
        }
    }
```
3. 停止代理
```
    AndroxyHelper.stopVpnService();
```


## 代理信息格式
### shadowsocks
```
ss://method:password@host:port
ss://base64encode(method:password@host:port)//使用base64加密后的串
```
### http
```
http://username:passsword@host:port
//或者
http://host:port
```