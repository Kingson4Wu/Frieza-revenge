package com.kxw.gRPC.schema.server;

import com.kxw.gRPC.schema.GreeterGrpc.GreeterImplBase;
import com.kxw.gRPC.schema.HelloReply;
import com.kxw.gRPC.schema.HelloRequest;
import io.grpc.stub.StreamObserver;

/**
 * @author Rayn on 2016/9/25.
 * @link https://my.oschina.net/Rayn/blog/751316
 * @email liuwei412552703@163.com.
 */
public class GrpcGreeterImpl extends GreeterImplBase {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {

        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}