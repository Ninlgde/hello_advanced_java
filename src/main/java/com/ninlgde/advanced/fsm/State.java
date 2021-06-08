package com.ninlgde.advanced.fsm;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ninlgde
 * @date: 5/10/21 2:24 PM
 */
public class State {

    /**
     * 状态编码
     */
    @Getter
    private String stateCode;

    /**
     * 当前状态下可允许执行的动作
     */
    @Getter
    private List<Transition> transitions = new ArrayList<>();

    public State(String stateCode, Transition... transitions) {
        this.stateCode = stateCode;
        for (Transition transition : transitions) {
            this.transitions.add(transition);
        }
    }

    // 添加动作
    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    @Override
    public String toString() {
        return stateCode;
    }
}
