package com.test.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.test.soap.Entities.ProductEntity;
import com.test.soap.service.ProductService;

import io.spring.guides.gs_producing_product_service.SaveProductRequest;
import io.spring.guides.gs_producing_product_service.GetProductByUidRequest;
import io.spring.guides.gs_producing_product_service.DeleteProductByUidRequest;
import io.spring.guides.gs_producing_product_service.SaveProductResponse;
import io.spring.guides.gs_producing_product_service.GetAllProductsResponse;
import io.spring.guides.gs_producing_product_service.GetProductByUidResponse;
import io.spring.guides.gs_producing_product_service.DeleteProductByUidResponse;

@Endpoint
public class ProductEndpoint {

    @Autowired
    ProductService productService;
    
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-product-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveProductRequest")
    @ResponsePayload
    public SaveProductResponse saveProduct (@RequestPayload SaveProductRequest request) {
        productService.saveProduct(new ProductEntity(request.getProduct()));
        SaveProductResponse response = new SaveProductResponse();
        response.setResult("success!");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAll () {
        GetAllProductsResponse response = new GetAllProductsResponse();
        response.setProductList(productService.getAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByUidRequest")
    @ResponsePayload
    public GetProductByUidResponse getByUid (@RequestPayload GetProductByUidRequest request) {
        GetProductByUidResponse response = new GetProductByUidResponse();
        response.setProduc(productService.getByUid(request.getUid()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteProductByUidRequest")
    @ResponsePayload
    public DeleteProductByUidResponse deleteByUid (@RequestPayload DeleteProductByUidRequest request) {
    DeleteProductByUidResponse response = new DeleteProductByUidResponse();
        productService.deleteByUid(request.getUid());
        response.setResult("Success!");
        return response;
    }
}
