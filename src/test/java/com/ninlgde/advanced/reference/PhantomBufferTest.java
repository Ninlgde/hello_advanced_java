package com.ninlgde.advanced.reference;

import org.junit.Test;

public class PhantomBufferTest {

    @Test
    public void testGet() {
        PhantomBuffer buffer = new PhantomBuffer();
        byte[] data = buffer.get(1024 * 1024 * 1024);
        System.out.println(data.length);
        data = buffer.get(100);
        System.out.println(data.length);
        data = buffer.get(1024 * 1024 * 1024 + 1); // 这里运行不过去了 data没有被回收..
        System.out.println(data.length);
    }
}
