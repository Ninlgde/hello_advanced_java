package com.ninlgde.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body)
            throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUsername("machicheng");
        builder.setProductName("Netty book");
        List<String> address = new ArrayList<>();
        address.add("AAAAAA AAAAA");
        address.add(" BBBBBBBB");
        address.add("BB CCCCCCC");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args)
            throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode:" + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After encode:" + req2.toString());
        System.out.println("Assert equal: --> " + req2.equals(req));
    }
}
