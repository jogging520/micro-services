package com.northbrain.session;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
public class Session {
    @Id
    private String  sessionId;
    private String  type;
    private String  userId;
    private String  roleId;
    private String  organizationId;
    private Date    firtLoginDate;
    private Date    loginDate;
    private Date    logoutDate;
    private String  status;
}
