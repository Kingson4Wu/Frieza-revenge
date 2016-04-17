package com.kxw.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.Environment;
import reactor.bus.selector.MatchAllSelector;
import reactor.fn.Function;
import reactor.io.buffer.Buffer;
import reactor.io.net.NetStreams;
import reactor.io.net.ReactorChannelHandler;
import reactor.io.net.Spec;
import reactor.io.net.config.ServerSocketOptions;
import reactor.io.net.http.HttpChannel;
import reactor.io.net.http.HttpServer;
import reactor.io.net.impl.netty.http.NettyHttpServer;

public class TestNettyHttpServer {

    public static void main(String[] args) {
        Environment.initialize();

        //1.--------

        Function<? super Spec.HttpServerSpec<Buffer,Buffer>,? extends Spec.HttpServerSpec<Buffer,Buffer>> configuringFunction =
                spec -> {
                    ServerSocketOptions options = new ServerSocketOptions();
                    options.backlog(1024);
                    options.reuseAddr(true);
                    options.keepAlive(true);
                    options.tcpNoDelay(true);
                    spec.options(options);
                    spec.listen("127.0.0.1",8080);

                    return spec;
                };

        HttpServer<Buffer,Buffer> server = NetStreams.httpServer(NettyHttpServer.class,configuringFunction)
                .route(new MatchAllSelector(), new ReactorChannelHandler<Buffer, Buffer, HttpChannel<Buffer, Buffer>>() {
                    @Override
                    public Publisher<Void> apply(HttpChannel<Buffer, Buffer> bufferBufferHttpChannel) {
                        return bufferBufferHttpChannel.writeBufferWith(new Publisher<Buffer>() {
                            @Override
                            public void subscribe(Subscriber<? super Buffer> subscriber) {
                                subscriber.onNext(Buffer.wrap("hello"));
                                subscriber.onComplete();
                            }
                        });
                    }
                });
        server.start();
        waitIndefinitely();

        //2.------
        NetStreams.httpServer("10.199.144.198",8080)
                .route(new MatchAllSelector(), new ReactorChannelHandler<Buffer, Buffer, HttpChannel<Buffer, Buffer>>() {
                    @Override
                    public Publisher<Void> apply(HttpChannel<Buffer, Buffer> bufferBufferHttpChannel) {
                        return bufferBufferHttpChannel.writeBufferWith(new Publisher<Buffer>() {
                            @Override
                            public void subscribe(Subscriber<? super Buffer> subscriber) {
                                subscriber.onNext(Buffer.wrap("hello"));
                                subscriber.onComplete();
                            }
                        });
                    }
                });
        server.start();
        waitIndefinitely();
    }

    public static void waitIndefinitely(){
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait();
            }
        }catch(Exception e){
                e.printStackTrace();
            }
        }

}
