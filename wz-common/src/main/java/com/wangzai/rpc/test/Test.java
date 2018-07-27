package com.wangzai.rpc.test;


import com.wangzai.rpc.client.RpcClient;
import com.wangzai.rpc.service.EchoService;


public class Test {

    public static void main(String[] args) {

        EchoService proxy = (EchoService) RpcClient.proxy(EchoService.class);

        for (int i = 0; i < 100; i++) {

            System.out.println(proxy.echo("wangzai" + ""));
        }

    }

}
