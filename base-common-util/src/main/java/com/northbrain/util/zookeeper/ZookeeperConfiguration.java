package com.northbrain.util.zookeeper;

import com.northbrain.util.model.CuratorProperty;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfiguration {
    private final CuratorProperty curatorProperty;

    public ZookeeperConfiguration(CuratorProperty curatorProperty) {
        this.curatorProperty = curatorProperty;
    }

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory
                .builder()
                .connectString(curatorProperty.getConnectString())
                .sessionTimeoutMs(curatorProperty.getSessionTimeoutMs())
                .connectionTimeoutMs(curatorProperty.getConnectionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(curatorProperty.getElapsedTimeMs(), curatorProperty.getRetryCount()))
                .namespace(curatorProperty.getNamespace())
                .build();
    }
}
