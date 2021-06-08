package com.ninlgde.zookeeper.servicediscovery.lb;


import com.ninlgde.jcip.annotations.Immutable;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Immutable
public final class RandomLoadBalance implements LoadBalance {

    private final List<String> serviceList;

    public RandomLoadBalance(List<String> list) {
        serviceList = list;
    }

    public String chooseServiceHost() {
        String result = "";
        if (!serviceList.isEmpty()) {
            int nextInt = ThreadLocalRandom.current().nextInt(serviceList.size());
            result = serviceList.get(nextInt);
        }
        return result;
    }
}
