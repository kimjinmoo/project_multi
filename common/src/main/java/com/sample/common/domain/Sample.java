package com.sample.common.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sample")
@Data
public class Sample {
    @Id
    private String id;
    private String name;
    private int age;
}
