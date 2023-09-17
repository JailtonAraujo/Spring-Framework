package com.test.soap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.test.soap.Entities.ProductEntity;
import com.test.soap.repository.ProductRepository;

import io.spring.guides.gs_producing_product_service.Product;
import io.spring.guides.gs_producing_product_service.ProductRes;
import io.spring.guides.gs_producing_product_service.ProductList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public void saveProduct (ProductEntity product) {
        productRepository.save(product);
    } 

    public ProductList getAll () {
        List<ProductEntity> entities = productRepository.findAll();
        ProductList products = new ProductList();

        for (ProductEntity productEntity : entities) {
            ProductRes product = new ProductRes();
            product.setUid(productEntity.getUid());
            product.setName(productEntity.getName());
            product.setDescription(productEntity.getDescription());
            product.setQuant(productEntity.getQuant());
            products.getProduct().add(product);
        }

        return products;
    }

    public Product getByUid (String uid) {
        ProductEntity entity = productRepository.findByUid(uid);
        Product product = new Product();
        product.setName(entity.getName());
        product.setDescription(entity.getDescription());
        product.setQuant(entity.getQuant());
        return product;
    }

    public void deleteByUid (String uid) {
        productRepository.deleteByUid(uid);
    } 
}
