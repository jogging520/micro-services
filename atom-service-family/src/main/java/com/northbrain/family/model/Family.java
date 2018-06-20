package com.northbrain.family.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain=true)
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Family {
    @Id
    private  String id;
    @NotNull
    private  String name;
    private  String address;
    private  String identify;
}
