package com.ninlgde.pearls;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnowflakeIdGeneratorTest {

    @Test
    public void nextId() {
        SnowflakeIdGenerator idWorker = new SnowflakeIdGenerator(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }
}