package com.northbrain.organization.model;

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
public class Region {
    @Id
    private String          regionId;          //会话ID
    private String          code;
    private String          name;
    private String          level;
    private String          status;
    private String          description;
    private Region[]        children;
}
