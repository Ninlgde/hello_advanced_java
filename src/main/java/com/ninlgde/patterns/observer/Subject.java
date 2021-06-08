package com.ninlgde.patterns.observer;

/**
 * @author: ninlgde
 * @date: 2020/4/28 16:00
 */
public interface Subject {
    /**
     * 注册添加观察者
     *
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     *
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 更新或通知变更所有观察者
     */
    void notifyObservers();
}
