package com.ninlgde.db.redis;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * BloomFilter Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 30, 2019</pre>
 */
public class BloomFilterTest {

    private static String chars;
    static {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            builder.append((char) ('a' + i));
        }
        chars = builder.toString();
    }

    private static String randomString(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int idx = ThreadLocalRandom.current().nextInt(chars.length());
            builder.append(chars.charAt(idx));
        }
        return builder.toString();
    }

    private static List<String> randomUsers(int n) {
        List<String> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            users.add(randomString(64));
        }
        return users;
    }

    private List<String> users;
    private List<String> usersTrain;
    private List<String> usersTest;

    private BloomFilter filter;

    private int falses;
    @Before
    public void before() throws Exception {
        users = randomUsers(100000);
        usersTrain = users.subList(0, users.size() / 2);
        usersTest = users.subList(users.size() / 2, users.size());

        filter = new BloomFilter(50000, 0.001);
    }

    @After
    public void after() throws Exception {
        System.out.printf("%d %d %.3f%%\n", falses, usersTest.size(), (float)falses / usersTest.size() * 100);
    }

    /**
     * Method: add(String value)
     */
    @Test
    public void test() throws Exception {
        // test add
        for (String user: usersTrain) {
            filter.add(user);
        }

        // test exists
        for (String user : usersTest) {
            boolean ret = filter.exists(user);
            if (ret) {
                falses++;
            }
        }
    }

    @Test
    public void testSingle() throws Exception {
        String user = "frinexnvkuqfnlldxhevzzprfxbcslhgnzamgchclrwytroklciobajradscunfw";
        filter.add(user);
        String test1 = "frinexnvkuqfnlldxhevzzprfxbcrwytroklciobajradscunfw";
        String test2 = "frinexnvkuqfnlldzzprfxbcslhgnzamgchclrwytroklciobajradscunfw";
        System.out.println(filter.exists(test1));
        System.out.println(filter.exists(test2));
    }

    @Test
    public void testThreadSafe1() throws Exception {
        new Thread(()-> {
            // test add
            for (String user: usersTrain) {
                filter.add(user);
            }
        }).start();
        new Thread(() -> {
            // test exists
            for (String user : usersTest) {
                boolean ret = filter.exists(user);
                if (ret) {
                    falses++;
                }
            }
        }).start();
        Thread.sleep(3000);
    }
} 
