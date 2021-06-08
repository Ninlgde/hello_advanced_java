package com.ninlgde.rpc.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author: ninlgde
 * @date: 3/22/21 7:29 PM
 */
public class UserClient {

    private static final Logger logger = Logger.getLogger(UserClient.class.getName());

    public static void main(String[] args) {
        try {

            TTransport transport = new TSocket("127.0.0.1", 9123);
            TProtocol protocol = new TBinaryProtocol(transport);

            UserService.Client client = new UserService.Client(protocol);
            transport.open();

            /**
             * 查询User列表
             */
            List<User> users = client.findUsersByName("wang");
            logger.info("client.findUsersByName()方法結果 == >" + users);

            /**
             * 保存User
             */
            boolean isUserSaved = client.save(new User(101, "WMJ"));
            logger.info("user saved result == > " + isUserSaved);

            /**
             * 删除用户
             */
            client.deleteByUserId(1002);

            transport.close();

        } catch (TTransportException e) {
            logger.severe("TTransportException==>" + e.getLocalizedMessage());
        } catch (UserNotFoundException e) {
            logger.severe("UserNotFoundException==>" + e.getLocalizedMessage());
        } catch (TException e) {
            logger.severe("TException==>" + e.getLocalizedMessage());
        }
    }
}
