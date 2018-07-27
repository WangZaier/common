package com.wangzai.rpc.client;

import com.wangzai.rpc.struct.Body;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.FutureTask;

/**
 * @author wangzai
 * @date 2018/6/23 上午3:32
 */
public class RpcClient {


    private static RpcClientHandler initClient() {
        RpcClientHandler rpcClientHandler = new RpcClientHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new ObjectEncoder());
                        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                        pipeline.addLast(rpcClientHandler);
                    }
                });
        try {
            bootstrap.connect("localhost", 8888).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rpcClientHandler;
    }

    public static <T> T proxy(Class<T> target) {
        return (T) Proxy.newProxyInstance(target.getClassLoader(), new Class<?>[]{target},
                (proxy, method, args) -> {
                    //构建消息体
                    Body body = new Body();
                    body.setClassName(target.getName());
                    body.setMethodName(method.getName());
                    body.setParameterTypes(method.getParameterTypes());
                    body.setParamValues(args);

                    //初始化rpcClient
                    RpcClientHandler rpcClientHandler = initClient();

                    // 设置参数
                    rpcClientHandler.setBody(body);

                    //设定异步回调
                    FutureTask<Object> task = new FutureTask<Object>(rpcClientHandler);
                    new Thread(task).start();
                    return task.get();
                });
    }
}

