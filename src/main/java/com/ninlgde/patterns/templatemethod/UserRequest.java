package com.ninlgde.patterns.templatemethod;

/**
 * @author: ninlgde
 * @date: 2020/4/28 17:53
 */
public class UserRequest extends BaseRequest {

    private static final long serialVersionUID = -5255944093621874878L;

    private String userName;

    public UserRequest() {
        super();
    }

    public UserRequest(long businessId) {
        super(businessId);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserRequest{userName='" + userName + '\'' + '}';
    }
}
