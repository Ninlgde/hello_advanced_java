package com.ninlgde.rpc.thrift;

import org.apache.thrift.TException;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author: ninlgde
 * @date: 3/22/21 7:26 PM
 */
public class UserServiceImpl implements UserService.Iface {


    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public boolean save(User user) throws TException {
        logger.info("方法save的参数user的内容==>" + user.toString());
        return true;
    }

    @Override
    public List<User> findUsersByName(String name) throws TException {
        logger.info("方法findUsersByName的参数name的内容==>" + name);
        return Arrays.asList(new User(1, "Wang"), new User(2, "Mengjun"));
    }

    @Override
    public void deleteByUserId(int userId) throws UserNotFoundException, TException {
        /**
         * 直接模拟抛出异常，用于测试
         */
        logger.info("方法deleteByUserId的参数userId的内容==>" + userId);
        throw new UserNotFoundException("1001", String.format("userId=%d的用户不存在", userId));
    }
}
