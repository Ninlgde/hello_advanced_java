package com.ninlgde.patterns.proxy;

/**
 * @author: ninlgde
 * @date: 2020/4/28 18:13
 */
public class Proxy implements Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        prePost();
        subject.request();
        afterPost();
    }

    @Override
    public void response() {
        prePost();
        subject.response();
        afterPost();
    }

    /**
     * 前置处理
     */
    protected void prePost() {
        System.out.println("前置处理完毕");
    }

    /**
     * 后置处理
     */
    protected void afterPost() {
        System.out.println("后置处理完毕");
    }
}
