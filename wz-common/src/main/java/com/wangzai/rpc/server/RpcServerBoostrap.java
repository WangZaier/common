package com.wangzai.rpc.server;

public class RpcServerBoostrap {

    public static void main(String[] args) throws InterruptedException {
        new RpcServer().start(8888);
    }

}
