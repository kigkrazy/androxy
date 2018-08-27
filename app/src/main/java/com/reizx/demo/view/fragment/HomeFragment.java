package com.reizx.demo.view.fragment;

import android.content.Intent;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ServiceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.reizx.androxy.AndroxyHelper;
import com.reizx.androxy.core.LocalVpnService;
import com.reizx.demo.R;
import com.reizx.demo.constant.SPConstants;
import com.reizx.demo.contract.HomeConstract;
import com.reizx.demo.presenter.HomePresenter;
import com.reizx.demo.util.AsfLog;
import com.reizx.demo.view.common.BaseFragment;
import com.white.easysp.EasySP;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static com.reizx.androxy.AndroxyHelper.START_VPN_SERVICE_REQUEST_CODE;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeConstract.View {

    private final String HTTP_PROXY_URL_TEMPLATE_NOUSER = "http://%s:%s";
    private final String HTTP_PROXY_URL_TEMPLATE = "http://%s:%s@%s:%s";
    private final String SS_PROXY_URL_TEMPLATE = "ss://%s:%s@%s:%s";
    @BindView(R.id.topbar)
    QMUITopBar mTopBar;

    @BindView(R.id.tv_app_home_log)
    TextView tvLob;

    @BindView(R.id.et_app_home_proxy_host)
    EditText etHost;
    @BindView(R.id.et_app_home_proxy_port)
    EditText etPort;
    @BindView(R.id.et_app_home_proxy_user)
    EditText etUser;
    @BindView(R.id.et_app_home_proxy_password)
    EditText etPassword;
    @BindView(R.id.et_app_home_proxy_encrypt)
    EditText etEncrypt;
    @BindView(R.id.rg_home_proxy_mode)
    RadioGroup rgProxyMode;
    @BindView(R.id.rb_home_proxy_mode_http)
    RadioButton rbHttp;
    @BindView(R.id.rb_home_proxy_mode_ss)
    RadioButton rbSs;

    @OnClick(R.id.btn_app_start_service)
    public void startProxyService() {
        try{
            Intent intent = LocalVpnService.prepare(baseActivity);
            if (intent != null) {
                startActivityForResult(intent, START_VPN_SERVICE_REQUEST_CODE);
                return;
            }

            String proxyUrl = getProxyUrl();
            AndroxyHelper.startVpnService(baseActivity, proxyUrl);
        }catch (Exception e){

        }
    }

    @OnClick(R.id.btn_app_stop_service)
    public void stopProxyService() {
        AndroxyHelper.stopVpnService();
//        if (!ServiceUtils.isServiceRunning(LocalVpnService.class)) {
//            AsfLog.d("the proxy is not running...");
//            return;
//        } else {
//            AsfLog.d("the proxy is running, that will stop it");
//            baseActivity.stopService(new Intent(baseActivity, LocalVpnService.class));
//        }
//        ToastUtils.showLong("stop service success...");
    }

    public String getProxyUrl() {
        if (rbHttp.isChecked() && StringUtils.isEmpty(etUser.getText())) {
            return String.format(HTTP_PROXY_URL_TEMPLATE_NOUSER, etHost.getText(), etPort.getText());
        }

        if (rbHttp.isChecked()) {
            return String.format(HTTP_PROXY_URL_TEMPLATE, etUser.getText(), etPassword.getText(), etHost.getText(), etPort.getText());
        }

        if (rbSs.isChecked()) {
            return String.format(SS_PROXY_URL_TEMPLATE, etEncrypt.getText(), etPassword.getText(), etHost.getText(), etPort.getText());
        }
        return "";
    }

    /**
     * butterknife处理radiobutton
     *
     * @param view
     * @param ischanged
     */
    @OnCheckedChanged({R.id.rb_home_proxy_mode_ss, R.id.rb_home_proxy_mode_http})
    public void OnCheckedChangeListener(CompoundButton view, boolean ischanged) {
        switch (view.getId()) {
            case R.id.rb_home_proxy_mode_ss:
                if (ischanged) {//注意：这里一定要有这个判断，只有对应该id的按钮被点击了，ischanged状态发生改变，才会执行下面的内容
                    ToastUtils.showLong("启动ss模式");
                }
                break;
            case R.id.rb_home_proxy_mode_http:
                if (ischanged) {
                    //这里写你的按钮变化状态的UI及相关逻辑
                    ToastUtils.showLong("启动http模式");
                }
                break;
            default:
                break;
        }
    }

    public void initConfigView(EasySP easySP) {
        String mode = easySP.getString(SPConstants.PROXY_MODE, "http");
        if (mode.equals("shadosocks")) {
            rgProxyMode.check(R.id.rb_home_proxy_mode_ss);
        } else {
            rgProxyMode.check(R.id.rb_home_proxy_mode_http);
        }

        String host = easySP.getString(SPConstants.PROXY_HOST, "192.168.10.202");
        etHost.setText(host);
        int port = easySP.getInt(SPConstants.PROXY_PORT, 8888);
        etPort.setText("" + port);
        String user = easySP.getString(SPConstants.PROXY_USER, "");
        etUser.setText(user);
        String passwrod = easySP.getString(SPConstants.PROXY_PASSWORD, "");
        etPassword.setText(passwrod);
        String encrypt = easySP.getString(SPConstants.PROXY_ENCRYPT, "aes-256-cfb");
        etEncrypt.setText(encrypt);
    }

    @Override
    public int getFragmentLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreateViewFinish() {
        super.onCreateViewFinish();
        initTopBar();
        initConfigView(presenter.getEastSP());
    }

    @Override
    protected void initInject() {
        getFragmentComponent().Inject(this);
    }

    public void initTopBar() {
        mTopBar.setTitle("主页");
    }

    @Override
    public void setCurrentIp(String ip) {

    }
}
