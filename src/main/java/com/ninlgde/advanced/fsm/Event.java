package com.ninlgde.advanced.fsm;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author: ninlgde
 * @date: 5/10/21 2:25 PM
 */
public class Event {
    /**
     * 事件标识(编码)
     */
    @Getter
    private String eventCode;

    /**
     * 附属的业务参数
     */
    @Getter
    @Setter
    private Map<Object, Object> attributes = null;

    public Event(String eventCode) {
        this.eventCode = eventCode;
    }

    public Event(String eventCode, Map<Object, Object> attributes) {
        this.eventCode = eventCode;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return eventCode;
    }
}
