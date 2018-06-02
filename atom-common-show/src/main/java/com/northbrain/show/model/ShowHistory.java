package com.northbrain.show.model;

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
public class ShowHistory {
    @Id
    private String      showHistoryId;   //ID
    private String      operationType;      //操作类型：增删改
    private String      showId;          //会话ID
    private String      item;
    private Integer     fee[];
    private String      status;
}
