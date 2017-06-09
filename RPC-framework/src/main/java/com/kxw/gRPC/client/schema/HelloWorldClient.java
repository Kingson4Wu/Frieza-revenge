package com.kxw.gRPC.client.schema;

import java.util.concurrent.TimeUnit;

import com.kxw.gRPC.schema.GreeterGrpc;
import com.kxw.gRPC.schema.GreeterGrpc.GreeterBlockingStub;
import com.kxw.gRPC.schema.HelloReply;
import com.kxw.gRPC.schema.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class HelloWorldClient {

    private final ManagedChannel channel;
    private final GreeterBlockingStub blockingStub;


    public HelloWorldClient(String host,int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
            .usePlaintext(true)
            .build();

        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void greet(String name){

        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);
        System.out.println(response.getMessage());

    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",50051);
        for(int i=0;i<5;i++){
            client.greet("托妞："+i);
        }
        client.shutdown();

    }
}