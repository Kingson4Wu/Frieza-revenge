package com.kxw.protobuf.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.google.protobuf.ExtensionRegistry;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * <a href='https://my.oschina.net/OutOfMemory/blog/294505'>@link</a>
 */
public class PbServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap(
            new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool()));
        final PbServerHandler handler = new PbServerHandler();
        final ExtensionRegistry registry = ExtensionRegistry.newInstance();
        Protocol.registerAllExtensions(registry);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("encoder", new StringEncoder());

                pipeline.addLast("frameDecoder",
                    new ProtobufVarint32FrameDecoder());
                pipeline.addLast("protobufDecoder", new ProtobufDecoder(
                    Protocol.Request.getDefaultInstance(), registry));
                pipeline.addLast("handler", handler);
                return pipeline;
            }
        });
        bootstrap.bind(new InetSocketAddress(8080));
        System.out.println("----------server is ok-----------");

    }
}