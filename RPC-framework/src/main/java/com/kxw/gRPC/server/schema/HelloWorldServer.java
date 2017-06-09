package com.kxw.gRPC.server.schema;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * https://my.oschina.net/MapleLi/blog/900665
 */
public class HelloWorldServer {


    private int port = 50051;
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
            .addService(new GrpcGreeterImpl())
            .build()
            .start();

        System.out.println("service start...");

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }


}