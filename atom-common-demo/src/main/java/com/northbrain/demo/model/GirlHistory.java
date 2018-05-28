package com.northbrain.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class GirlHistory {
    @Id
    private String      girlHistoryId;   //ID
    private String      operationType;      //操作类型：增删改
    private String      girlId;          //会话ID
    private String      firstName;
    private String      lastName;
    private String      mobiles[];
    private String      status;
}

