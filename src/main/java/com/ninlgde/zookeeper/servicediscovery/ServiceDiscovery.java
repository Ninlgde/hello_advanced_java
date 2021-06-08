package com.ninlgde.zookeeper.servicediscovery;

import com.ninlgde.jcip.annotations.ThreadSafe;
import com.ninlgde.zookeeper.servicediscovery.lb.LoadBalance;
import com.ninlgde.zookeeper.servicediscovery.lb.RandomLoadBalance;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@ThreadSafe
public class ServiceDiscovery {
    // singleton
    public static class ServiceDiscoveryHolder {
        protected static ServiceDiscovery instance = new ServiceDiscovery();
    }

    public static ServiceDiscovery getInstance() {
        return ServiceDiscoveryHolder.instance;
    }

    private ServiceDiscovery() {
    }

    private static final String BASE_SERVICE = "/service";
    private ZooKeeper zooKeeper;
    private ConcurrentMap<String, LoadBalance> lbMap = new ConcurrentHashMap<>();
    private final Set<String> watchSet = new HashSet<>();

    /**
     * 初始化参数
     * @param properties
     */
    public void init(Properties properties) {
        try {
            zooKeeper = new ZooKeeper(properties.getProperty("ZOOKEEPER_ADDR"), 5000,
                    (watchedEvent) -> {
                        if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                            synchronized (watchSet) {
                                for (String service : watchSet) {
                                    if (watchedEvent.getPath().equals(BASE_SERVICE + service)) {
                                        System.out.println("***" + service + "注册到zk的服务信息发生变化***");
                                        updateServiceList(service);
                                    }
                                }
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String discover(String serviceName) {
        synchronized (watchSet) {
            // 首次取一次列表
            if (!watchSet.contains(serviceName))
                updateServiceList(serviceName);
            watchSet.add(serviceName);
        }
        return lbMap.get(serviceName).chooseServiceHost();
    }

    @Deprecated
    private void updateServicesList() {
        synchronized (watchSet) {
            for (String service : watchSet) {
                updateServiceList(service);
            }
        }
    }

    private void updateServiceList(String service) {
        List<String> list = new ArrayList<>();
        try {
            List<String> children = zooKeeper.getChildren(BASE_SERVICE + service, true);
            for (String subNode : children) {
                byte[] data = zooKeeper.getData(BASE_SERVICE + service + "/" + subNode, false, null);
                String host = new String(data, StandardCharsets.UTF_8);
                list.add(host);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        lbMap.put(service, new RandomLoadBalance(list));
    }
}
