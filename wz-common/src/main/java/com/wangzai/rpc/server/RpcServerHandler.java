package com.wangzai.rpc.server;

import com.wangzai.rpc.struct.Body;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class RpcServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof Body) {
            Body body = (Body) msg;

            String className = body.getClassName() + "Impl";
            String methodName = body.getMethodName();
            Class<?>[] parameterTypes = body.getParameterTypes();
            Object[] paramValues = body.getParamValues();

            Class<?> clazz = Class.forName(className);
            Object bean = clazz.newInstance();
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            Object result = method.invoke(bean, paramValues);

            ctx.writeAndFlush(result);
            ctx.close();
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
