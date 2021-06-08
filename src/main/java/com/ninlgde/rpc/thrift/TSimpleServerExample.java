package com.ninlgde.rpc.thrift;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import java.util.logging.Logger;

/**
 * @author: ninlgde
 * @date: 3/22/21 4:35 PM
 */
public class TSimpleServerExample {
    private static final Logger logger = Logger.getLogger(TSimpleServerExample.class.getName());

    private static final int SERVER_PORT = 9123;

    public static void main(String[] args) {
        try {

            /**
             * 1. 创建Transport
             */
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TServer.Args tArgs = new TServer.Args(serverTransport);

            /**
             * 2. 为Transport创建Protocol
             */
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            // tArgs.protocolFactory(new TCompactProtocol.Factory());
            // tArgs.protocolFactory(new TJSONProtocol.Factory());

            /**
             * 3. 为Protocol创建Processor
             */
            TProcessor tprocessor = new UserService.Processor<UserService.Iface>(new UserServiceImpl());
            tArgs.processor(tprocessor);


            /**
             * 4. 创建Server并启动
             *
             * org.apache.thrift.server.TSimpleServer - 简单的单线程服务模型，一般用于测试
             */
            TServer server = new TSimpleServer(tArgs);
            logger.info("UserService TSimpleServer start ....");
            server.serve();


        } catch (Exception e) {
            logger.severe("Server start error!!!" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
