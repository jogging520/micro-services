package com.northbrain.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix="session.jwt.token")
public class JsonWebToken {
    String key;
    String companyId;
    String audience;
    String issuer;
    String expireTtl;
}
