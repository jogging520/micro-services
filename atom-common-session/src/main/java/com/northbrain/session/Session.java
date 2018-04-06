package com.northbrain.session;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@ConfigurationProperties(prefix="session.jwt.token")
public class Session {
    @Id
    private String id;
    private int userId;
    private int roleId;
    private int organizationId;

    //session相关的要放到util里面ConfigurationProperties
}
