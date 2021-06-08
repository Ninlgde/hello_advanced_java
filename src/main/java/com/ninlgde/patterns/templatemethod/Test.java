package com.ninlgde.patterns.templatemethod;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:01
 */
public class Test {
    public static void main(String[] args) {
        UserInterface userService = new UserInterface();
        UserRequest request = new UserRequest();
        // 暂且用时间毫秒数作为请求ID
        request.setBusinessId(System.currentTimeMillis());
        request.setUserName("");

        // 非法请求
        Result<User> result = userService.templateMethod(request);
        System.out.println("返回结果：" + result.toString());

        // 正常请求
        request.setBusinessId(System.currentTimeMillis());
        request.setUserName("张三");
        result = userService.templateMethod(request);
        System.out.println("返回结果：" + result.toString());
    }
}
