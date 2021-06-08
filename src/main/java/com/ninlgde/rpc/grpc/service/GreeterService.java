package com.ninlgde.rpc.grpc.service;

import com.ninlgde.rpc.grpc.proto.helloworld.GreeterGrpc;
import com.ninlgde.rpc.grpc.proto.helloworld.HelloReply;
import com.ninlgde.rpc.grpc.proto.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: ninlgde
 * @date: 3/24/21 1:42 AM
 */
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    private static final Logger logger = Logger.getLogger(GreeterService.class.getName());

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        logger.log(Level.WARNING, "sayHello " + req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
//        HelloReply reply2 = HelloReply.newBuilder().setMessage("Hello2 " + req.getName()).build();
//        responseObserver.onNext(reply2);
        responseObserver.onCompleted();
    }
}
