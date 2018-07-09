package com.northbrain.util.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Accessors(chain=true)
@ToString
@NoArgsConstructor
@ConfigurationProperties(prefix=Constants.UTIL_CURATOR_PROPERTY_PREFIX)
public class CuratorProperty {
    private Integer         retryCount;             //重试次数
    private Integer         elapsedTimeMs;          //重试间隔时间
    private String          connectString;          //zookeeper 地址
    private Integer         sessionTimeoutMs;       //session超时时间
    private Integer         connectionTimeoutMs;    //连接超时时间
    private String          namespace;              //总命名空间
}
