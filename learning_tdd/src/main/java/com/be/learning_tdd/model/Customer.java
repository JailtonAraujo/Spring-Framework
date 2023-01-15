package com.be.learning_tdd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 189440126344399806L;

    @Id
    private String id;

    private String name;

    private List<Order> orders;

}
