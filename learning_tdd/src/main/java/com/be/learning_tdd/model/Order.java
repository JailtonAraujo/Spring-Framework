package com.be.learning_tdd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Document(collection = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 420731084208505385L;

    @Id
    private String id;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private Book book;

    private String createAt;
}
