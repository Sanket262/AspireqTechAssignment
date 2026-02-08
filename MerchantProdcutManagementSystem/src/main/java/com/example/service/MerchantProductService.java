package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.component.ProductMapper;
import com.example.dto.CreateProductRequest;
import com.example.dto.ProductResponse;
import com.example.dto.UpdateProductRequest;
import com.example.entity.Product;
import com.example.exception.ForbiddenException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class MerchantProductService
{
	@Autowired
	private  ProductRepository ProductRepo;
	
	@Autowired
	private ProductMapper productmapper;
	
	public ProductResponse createProduct(String merchantId,CreateProductRequest request)
	{
		
		if(ProductRepo.existsByMerchantIdAndProductNameIgnoreCase(merchantId,request.getProductName()))
		{
			throw new IllegalArgumentException("Product name is Already Exist");
		}
		
		
		Product product=new Product();
		product.setMerchantId(merchantId);
		product.setProductName(request.getProductName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setAvailable(request.getAvailable() != null ? request.getAvailable():true);
		
		Product savedProduct=ProductRepo.save(product);
		
		return productmapper.toResponse(savedProduct);

	}

	public ProductResponse getProduct(String merchantId, Long productId) 
	{
		Product product=getOwnedProduct(merchantId,productId);		                
		// TODO Auto-generated method stub
		return productmapper.toResponse(product);
	}

	/*
	public List<ProductResponse> getProductAll(String merchantId)
	{
	   List<Product> products= ProductRepo.findAllByMerchantIdAndIsDeleted(merchantId,false);
	   
	   List<ProductResponse> response=new ArrayList<>();
	   
	   for(Product product:products)
	   {
		   response.add(productmapper.toResponse(product));
	   }
	   return response;
		// TODO Auto-generated method stub
		
	}
   */

	public ProductResponse updateProduct(String merchantId, Long productId, UpdateProductRequest request)
	{
		
		// TODO Auto-generated method stub
		Product product=getOwnedProduct(merchantId,productId);
              
		if(request.getDescription()!=null)
		{
			product.setDescription(request.getDescription());
		}
		if(request.getPrice()!=null)
		{
			product.setPrice(request.getPrice());
		}
		if(request.getIsAvailable()!=null)
		{
			product.setAvailable(request.getIsAvailable());
		}
		
		Product updateProduct=ProductRepo.save(product);
		
		return productmapper.toResponse(updateProduct);
	}

	public void deleteProduct(String merchantId, Long productId) {
		// TODO Auto-generated method stub
		Product product=getOwnedProduct(merchantId,productId);	            
		product.setDeleted(true);
		ProductRepo.save(product);	
	}
	
	private Product getOwnedProduct(String merchantId, Long productId) {

	    Product product = ProductRepo
	            .findByIdAndIsDeletedFalse(productId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Product Not Found"));

	    if (!product.getMerchantId().equals(merchantId)) {
	        throw new ForbiddenException("Access Denied");
	    }

	    return product;
	}
	

	public Page<ProductResponse>  getProductAllWithPage(String merchantId, int page, int size) {
		// TODO Auto-generated method stub
	    Pageable pageable = PageRequest.of(page, size);

	    Page<Product> productPage =
	            ProductRepo.findAllByMerchantIdAndIsDeleted(
	                    merchantId, false, pageable);
	    List<ProductResponse> responseList = new ArrayList<>();

	    for (Product product : productPage.getContent()) {
	        responseList.add(productmapper.toResponse(product));
	    }
		
	    return new PageImpl<>(
	            responseList,
	            pageable,
	            productPage.getTotalElements()
	    );
		
	}


	
	
	

	

}
