package com.be.learning_tdd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = -5789329380288006598L;

    @Id
    private String id;

    private String name;

    private Float price;

}
