package com.ninlgde.advanced.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class ReflectTest {
    public static class Result<T> {

//        @JSONField(serialize = false, deserialize = false)
        private String msg;

        private List<T> module;

        @JSONField(name = "mmsg")
        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<T> getModule() {
            return module;
        }

        public void setModule(List<T> module) {
            this.module = module;
        }
    }

    public static class User {

        private Long user_id;

        private String user_name;

        public User() {

        }

        public User(Long userId, String name) {
            this.user_id = userId;
            this.user_name = name;
        }

        public Long getUser_id() {
            return user_id;
        }

        public void setUser_id(Long user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

    }

    public static void main(String[] args) {
        Result<User> r = new Result<>();

        r.setMsg("msg");

        List<User> users = new ArrayList<>();
        users.add(new User(1L, "hehe"));
        users.add(new User(2L, "haha"));

        r.setModule(users);

        String js = JSON.toJSONString(r);

        System.out.println(js);

        Result<User> obj = JSON.parseObject(js, new TypeReference<Result<User>>(){});

        User user = obj.getModule().get(0);
        System.out.println(user);
    }
}
