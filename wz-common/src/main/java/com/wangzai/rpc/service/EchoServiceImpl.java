package com.wangzai.rpc.service;

public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String name) {
        return "hello" + name + "!";
    }
}
