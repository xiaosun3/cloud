package com.cloud.common.rpc;


import com.cloud.common.dynamicproxy.service.TicketService;
import com.cloud.common.dynamicproxy.service.TicketServiceImpl;

/**
 * @auth Administrator
 */
public class RpcClient {

    // 调用服务
    public static void main(String[] args) {
        RpcProxyClient<TicketServiceImpl> rpcClient = new RpcProxyClient<TicketServiceImpl>();

        TicketService hello = rpcClient.proxyClient(TicketServiceImpl.class);
        String s = hello.inquire();
        System.out.println(s);
    }


}
