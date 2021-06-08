package com.ninlgde.patterns.templatemethod;

/**
 * @author: ninlgde
 * @date: 2020/4/28 17:52
 */
public class UserInterface extends AbstractInterface<User> {
    @Override
    public Result<User> checkParam(BaseRequest request) {
        UserRequest userRequest = (UserRequest)request;
        if(userRequest.getUserName() == null || userRequest.getUserName().equals("")) {
            return new Result<>(ReturnCode.INVALID_PARAM, "userName is empty", null);
        }
        return new Result<>(ReturnCode.SUCCESS, "success", null);
    }

    @Override
    public Result<User> execute(BaseRequest request) {
        UserRequest userRequest = (UserRequest)request;
        // TODO : 根据userName查询用户信息逻辑。此处模拟成功查询
        User user = new User(1L, "张三", 20, "男");

        return new Result<>(ReturnCode.SUCCESS, "success", user);
    }
}
