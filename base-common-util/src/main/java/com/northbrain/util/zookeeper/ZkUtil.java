package com.northbrain.util.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

@Component
public class ZkUtil {
    private final CuratorFramework curatorFramework;

    public ZkUtil(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }


}
