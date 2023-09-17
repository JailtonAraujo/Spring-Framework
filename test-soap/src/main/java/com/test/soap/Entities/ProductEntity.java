package com.test.soap.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.spring.guides.gs_producing_product_service.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class ProductEntity {

    public ProductEntity (Product product) {
        this.description = product.getDescription();
        this.name = product.getName();
        this.quant = product.getQuant();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false)
    private String uid;

    private String name;

    private int quant;

    private String description; 
}
