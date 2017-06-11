package com.kxw.protobuf.netty;

import com.kxw.protobuf.netty.Protocol.Login;
import com.kxw.protobuf.netty.Protocol.Request;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class PbClientHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
        throws Exception {
        System.out.println(e.getMessage());

        Login login = Login.newBuilder().setUser("ksfzhaohui")
            .setPswd("11111111").build();
        Request.Builder builder = Request.newBuilder();
        builder.setCmdId(100);
        builder.setExtension(Protocol.login, login);
        Request request = builder.build();

        e.getChannel().write(request);
    }
}