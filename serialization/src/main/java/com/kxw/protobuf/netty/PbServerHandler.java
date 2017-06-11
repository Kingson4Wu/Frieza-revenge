package com.kxw.protobuf.netty;

import com.kxw.protobuf.netty.Protocol.Login;
import com.kxw.protobuf.netty.Protocol.Request;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class PbServerHandler extends SimpleChannelHandler {

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
        throws Exception {
        e.getChannel().write("连接成功");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
        throws Exception {
        Request request = (Request)e.getMessage();
        System.out.println("cmd:" + request.getCmdId());
        Login login = request.getExtension(Protocol.login);
        System.out.println("user:" + login.getUser());
        System.out.println("psw:" + login.getPswd());
    }
}