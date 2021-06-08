package com.ninlgde.advanced.fsm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ninlgde
 * @date: 5/10/21 2:26 PM
 */
public abstract class Transition {

    /**
     * 触发事件
     */
    @Getter
    private String eventCode;

    /**
     * 触发当前状态
     */
    @Getter
    private State currState;

    /**
     * 触发后状态
     */
    @Getter
    private State nextState;

    public Transition(String eventCode, State currState, State nextState) {
        super();
        this.eventCode = eventCode;
        this.currState = currState;
        this.nextState = nextState;
    }

    /**
     * 执行动作
     *
     * @param event
     * @return
     * @author 张振伟
     */
    public State execute(Event event) {
        System.out.println(String.format("当前是：%s 状态，执行：%s 操作后，流转成：%s 状态。", currState, eventCode, nextState));
        if (this.doExecute(event)) {
            return this.nextState;
        } else {
            return null;
        }
    }

    /**
     * 执行动作的具体业务
     *
     * @param event
     * @return
     * @author 张振伟
     */
    protected abstract boolean doExecute(Event event);
}
