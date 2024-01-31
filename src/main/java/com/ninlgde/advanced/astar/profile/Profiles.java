package com.ninlgde.advanced.astar.profile;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ninlgde
 * @date 2022/9/23 12:04
 */
public class Profiles {

    public static boolean OPEN = true;
    private final static Map<String, ProfileNode> nodes = new ConcurrentHashMap<>(100);
    private static int maxLength;
    public static final ThreadLocal<ProfileNode> session = new ThreadLocal<>();

    public Profiles() {
    }

    private static ProfileNode getNode(String title) {
        synchronized (nodes) {
            ProfileNode node = nodes.get(title);
            if (node == null) {
                node = new ProfileNode(title);
                nodes.put(title, node);
                maxLength = Math.max(maxLength, title.length());
            }

            return node;
        }
    }

    public static void reset() {
        nodes.clear();
    }

    public static synchronized String[] dump() {
        ArrayList<ProfileNode> list;
        synchronized (nodes) {
            list = new ArrayList<>(nodes.values());
        }

        String title = String.format("%-" + (maxLength + 4) + "s\t%8s\t%8s\t%8s\t%8s\t%8s", "title", "cnt", "avg(us)", "min(us)", "max(us)", "fst(us)");
        System.out.println(title);
        String[] result = new String[list.size() + 1];
        int i = 0;
        int var9 = i + 1;
        result[i] = title;

        for (ProfileNode node : list) {
            if (node.min == 9223372036854775807L) {
                result[var9++] = null;
            } else {
                String template = "%-" + (maxLength + 4) + "s\t%8d\t%8d\t%8d\t%8d\t%8d";
                String tmp = String.format(template, node.title, node.cnt, (int) ((node.cnt == 0L ? 0L : node.sum / node.cnt) / 1000L), node.min / 1000L, node.max / 1000L, node.fst / 1000L);
                result[var9++] = tmp;
                System.out.println(tmp);
            }
        }

        return result;
    }

    public static void sampling(String title, long start, long end) {
        ProfileNode info = getNode(title);
        info.start = start;
        info.end = end;
        info.duration = end - start;
        info.submit();
    }

    public static void start(String title) {
        ProfileNode info = session.get();
        if (info != null) {
            info.reset();
            session.remove();
        }

        info = getNode(title);
        session.set(info);
        info.start = System.nanoTime();
    }

    public static void step() {
        long now = System.nanoTime();
        ProfileNode info = session.get();
        if (info != null) {
            long duration = now - info.start;
            info.duration += duration;
            info.start = System.nanoTime();
        }
    }

    public static void step(String message) {
        long now = System.nanoTime();
        ProfileNode info = session.get();
        if (info != null) {
            long duration = now - info.start;
            info.duration += duration;
            info.start = System.nanoTime();
        }
    }

    public static void end(String message) {
        long now = System.nanoTime();
        ProfileNode info = session.get();
        if (info != null) {
            long duration = now - info.start;
            info.duration += duration;
            info.submit();
            session.remove();
        }
    }

    public static void localDebug() {
    }
}
