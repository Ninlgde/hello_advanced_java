package com.ninlgde.db.redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ThreadLocalRandom;

public class PfTest {
    //    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1", 30379);
//        for (int i = 0; i < 1000; i++) {
//            jedis.pfadd("ninlgde", "user" + i);
//            long total = jedis.pfcount("ninlgde");
//            if (total != i + 1) {
//                System.out.printf("%d %d\n", total, i + 1);
//                break;
//            }
//        }
//        jedis.close();
//    }

    public static void main(String[] args) {
        for (int i = 100000; i < 1000000; i += 100000) {
            HyperLogLog hll = new HyperLogLog();
            for (int j = 0; j < i; j++)
                hll.add(ThreadLocalRandom.current().nextLong(1L << 32));
            double count = hll.count();
            System.out.printf("%d %.2f %.2f\n", i, count, Math.abs(count - i) / i);
        }
    }
}
