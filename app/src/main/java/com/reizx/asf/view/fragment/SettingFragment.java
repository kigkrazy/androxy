package com.reizx.asf.view.fragment;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.Printer;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.asf.R;
import com.reizx.asf.contract.SettingContract;
import com.reizx.asf.presenter.SettingPresenter;
import com.reizx.asf.util.AsfLog;
import com.reizx.asf.util.RxUtil;
import com.reizx.asf.util.XLogUtilExt;
import com.reizx.asf.view.common.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SettingFragment extends BaseFragment<SettingPresenter> implements SettingContract.View {
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_setting_page_show_ip_des)
    TextView tvIpStatus;
    Disposable ds;

    @SuppressLint("CheckResult")
    @OnClick(R.id.btn_setting_page_test)
    public void clickTest(){
        if (ds != null && !ds.isDisposed()){
            AsfLog.d("dispose the subscribe...");
            ds.dispose();
            ds = null;
            return;
        }

        AsfLog.d("click setting page test");
        ds = Flowable.interval(1,  TimeUnit.SECONDS)
                .onBackpressureDrop()
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        AsfLog.d("the inter ... " + aLong);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AsfLog.d("flow err : " + throwable);
                    }
                });
    }

    @OnClick(R.id.btn_setting_page_xlog)
    public void printXlog(){
        XLogUtilExt.d("xxxxxxxxx");
        XLogUtilExt.d("xxxxxxxxx");
        XLogUtilExt.d("xxxxxxxxx");
        XLogUtilExt.d("xxxxxxxxx");
        XLogUtilExt.d("xxxxxxxxx");
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initAllMembersView() {
        super.initAllMembersView();
        initTopBar();
        initXLog();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar() {
        mTopBar.setTitle("设置");
    }

    public void initXLog(){
        Printer androidPrinter = new AndroidPrinter();                                      // 通过 android.util.Log 打印日志的打印器

        @SuppressLint("SdCardPath")
        XLogUtilExt.HistoryDateFileNameGenerator fileNameGenerator = new XLogUtilExt.HistoryDateFileNameGenerator(3, "/sdcard/sdf/");
        XLogUtilExt.Setter.newSetter()
                .tag("asf-log")
                .printers(androidPrinter)
                .set();
//        LogConfiguration config = new LogConfiguration.Builder()
//                .logLevel(LogLevel.ALL)             // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
//                .tag("MY_TAG")                                         // 指定 TAG，默认为 "X-LOG"
//                //.t()                                                   // 允许打印线程信息，默认禁止
//                //.st(2)                                                 // 允许打印深度为2的调用栈信息，默认禁止
//                //.b()                                                   // 允许打印日志边框，默认禁止
//                //.jsonFormatter(new MyJsonFormatter())                  // 指定 JSON 格式化器，默认为 DefaultJsonFormatter
//                //.xmlFormatter(new MyXmlFormatter())                    // 指定 XML 格式化器，默认为 DefaultXmlFormatter
//                //.throwableFormatter(new MyThrowableFormatter())        // 指定可抛出异常格式化器，默认为 DefaultThrowableFormatter
//                //.threadFormatter(new MyThreadFormatter())              // 指定线程信息格式化器，默认为 DefaultThreadFormatter
//                //.stackTraceFormatter(new MyStackTraceFormatter())      // 指定调用栈信息格式化器，默认为 DefaultStackTraceFormatter
//                //.borderFormatter(new MyBoardFormatter())               // 指定边框格式化器，默认为 DefaultBorderFormatter
//                //.addObjectFormatter(AnyClass.class,                    // 为指定类添加格式化器
//                //        new AnyClassObjectFormatter())                     // 默认使用 Object.toString()
//                //.addInterceptor(new BlacklistTagsFilterInterceptor(    // 添加黑名单 TAG 过滤器
//                //        "blacklist1", "blacklist2", "blacklist3"))
//                //.addInterceptor(new MyInterceptor())                   // 添加一个日志拦截器
//                .build();
//        Printer androidPrinter = new AndroidPrinter();                      // 通过 android.util.Log 打印日志的打印器
//        Printer filePrinter = new FilePrinter                               // 打印日志到文件的打印器
//                .Builder("/sdcard/xlog/")                       // 指定保存日志文件的路径
//                .fileNameGenerator(new DateFileNameGenerator())             // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
//                .backupStrategy(new FileSizeBackupStrategy(50))   // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
//                .logFlattener(new DefaultFlattener())                       // 指定日志平铺器，默认为 DefaultFlattener
//                .build();
//        XLog.init(config, androidPrinter, filePrinter);
//        XLog.d("df");
    }

    @Override
    public void showIpStatus(String msg) {
        tvIpStatus.setText(msg);
    }
}
