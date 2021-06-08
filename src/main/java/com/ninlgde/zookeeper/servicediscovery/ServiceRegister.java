package com.ninlgde.zookeeper.servicediscovery;

import com.ninlgde.jcip.annotations.ThreadSafe;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

@ThreadSafe
public class ServiceRegister {
    private  static final String BASE_SERVICE = "/service";

    public static void register(String serviceName, String address,int port) {
        /**
         * 在zk创建根节点path,在根节点下创建临时子节点用于存放服务ip和端口
         */
        try {
            String path = BASE_SERVICE + serviceName;
            ZooKeeper zooKeeper = new ZooKeeper("172.16.4.79:2181",5000,(watchedEvent) -> {});
            System.out.println(zooKeeper);
            Thread.sleep(2000);
            Stat exists = zooKeeper.exists(BASE_SERVICE + serviceName, false);
            //先判断服务根路径是否存在
            if (exists == null){
                zooKeeper.create(BASE_SERVICE + serviceName,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //将服务的ip和端口作为临时带序号的子节点
            String server_path = address+":"+port;
            zooKeeper.create(path + "/child",server_path.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("服务注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
