package com.wangzai.rpc.client;

import com.wangzai.rpc.struct.Body;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class RpcClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    private Object result;
    private Body body;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        result = msg;
        notify();
    }


    @Override
    public synchronized Object call() throws InterruptedException {
        context.writeAndFlush(body);
        wait();
        return result;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
