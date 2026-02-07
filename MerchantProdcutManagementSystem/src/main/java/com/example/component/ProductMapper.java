package com.example.component;

import org.springframework.stereotype.Component;

import com.example.dto.ProductResponse;
import com.example.entity.Product;

@Component
public class ProductMapper {

	public static ProductResponse toResponse(Product product) 
	{
		ProductResponse response=new ProductResponse();
		response.setId(product.getId());
		response.setProductName(product.getProductName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setIsAvailable(product.isAvailable());
		response.setCreatedAt(product.getCreatedAt());
		// TODO Auto-generated method stub
		return response;
	}

}
