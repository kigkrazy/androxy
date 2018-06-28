# 安卓项目初始化框架
本框架是为了方便开发，整合一些最基本的功能。在开发新项目的时候可以基于这个项目进行修改。

## 项目介绍
项目引用了几个大框架`Design + MVP + RxJava2 + Retrofit + Dagger2 + QMUI`进行项目初始化搭建。

## 使用方法
直接下载项目，修改项目`包名`，`项目名`，`applicationId`等关键信息就可以进行开发了。

### 添加Retrofit2请求接口(此处我们以`IpApi`为例)
1. 在`com.reizx.asf.model.retrofit.api`包下建立相关接口类(`IpApi`)
2. 在`com.reizx.zues.di.qualifier`包下添加相关的`qualifier`类(`IpQualifier`)
3. `com.reizx.asf.di.component.AppComponent`类中添加相应的`component`接口
```java
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context

    //对所有的请求进行处理
    IpApi getIpApi();//IP请求接口 -----此处为添加的
}
```
4. 在`com.reizx.asf.di.module.HttpModule`填写相关Retrofit接口与`IpApi`接口
```java
@Module
public class HttpModule {
    //...
    @Singleton
    @Provides
    @IpQualifier
    Retrofit provideIpRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, IpApi.HOST);
    }

    @Singleton
    @Provides
    IpApi provideIpApi(@IpQualifier Retrofit retrofit) {
        return retrofit.create(IpApi.class);
    }
    //...
}
```

## 项目规范
[安卓个人开发规范开发规范](https://kigkrazy.github.io/android/2018/01/11/android-develop-framework/)

## 相关依赖库推荐
[andrutil](https://github.com/kigkrazy/andrutil)  

## 更新
[更新日志](UPDATE_LOG.md)