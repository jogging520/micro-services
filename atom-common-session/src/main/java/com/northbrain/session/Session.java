package com.northbrain.session;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Session {
    @Id
    private String id;
    private String userId;
    private String roleId;
    private String organizationId;
}
