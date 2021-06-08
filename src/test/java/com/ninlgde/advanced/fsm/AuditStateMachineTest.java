package com.ninlgde.advanced.fsm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: ninlgde
 * @date: 5/10/21 2:32 PM
 */
public class AuditStateMachineTest {

    @Test
    public void test() {
        StateMachine sm = new AuditStateMachine();
        State state = sm.execute(AuditStateMachine.StateCodeContents.PENDING, new Event(AuditStateMachine.EventCodeContents.REFUSE));
        System.out.println(state.getStateCode());
    }
}