package com.ninlgde.zookeeper.servicediscovery.lb;

import java.util.ArrayList;
import java.util.List;

public interface LoadBalance {

    default String chooseServiceHost() {
        return null;
    }
}
