package com.reizx.androxy.core;


import com.reizx.androxy.tunnel.Config;
import com.reizx.androxy.tunnel.RawTunnel;
import com.reizx.androxy.tunnel.Tunnel;
import com.reizx.androxy.tunnel.httpconnect.HttpConnectConfig;
import com.reizx.androxy.tunnel.httpconnect.HttpConnectTunnel;
import com.reizx.androxy.tunnel.shadowsocks.ShadowsocksConfig;
import com.reizx.androxy.tunnel.shadowsocks.ShadowsocksTunnel;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TunnelFactory {

    public static Tunnel wrap(SocketChannel channel, Selector selector) {
        return new RawTunnel(channel, selector);
    }

    public static Tunnel createTunnelByConfig(InetSocketAddress destAddress, Selector selector) throws Exception {
        if (destAddress.isUnresolved()) {
            Config config = ProxyConfig.Instance.getDefaultTunnelConfig(destAddress);
            if (config instanceof HttpConnectConfig) {
                return new HttpConnectTunnel((HttpConnectConfig) config, selector);
            } else if (config instanceof ShadowsocksConfig) {
                return new ShadowsocksTunnel((ShadowsocksConfig) config, selector);
            }
            throw new Exception("The config is unknow.");
        } else {
            return new RawTunnel(destAddress, selector);
        }
    }

}
