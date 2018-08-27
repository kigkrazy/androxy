# androxy
安卓下简单的HTTP代理以及SSsock代理服务调用库。

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