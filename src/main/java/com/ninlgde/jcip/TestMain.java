package com.ninlgde.jcip;

import static java.util.concurrent.TimeUnit.SECONDS;
import com.ninlgde.jcip.StripedMap;

import java.util.UUID;

public class TestMain {
    public static void main(String[] args)
            throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        System.out.println(generator.get());

        final StripedMap<String, String> map = new StripedMap<>(64);

        for (int i = 0; i < 256; i++) {
            String uuid = UUID.randomUUID().toString();
            map.put(uuid, uuid);
        }

        map.printAll();
    }
}
