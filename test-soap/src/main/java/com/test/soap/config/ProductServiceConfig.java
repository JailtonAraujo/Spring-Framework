package com.test.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class ProductServiceConfig {
    
    @Bean(name = "products")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema productSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ProductsPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-product-service");
		wsdl11Definition.setSchema(productSchema);
		return wsdl11Definition;
	}

    @Bean
	public XsdSchema productSchema() {
		return new SimpleXsdSchema(new ClassPathResource("product.xsd"));
	}
}
