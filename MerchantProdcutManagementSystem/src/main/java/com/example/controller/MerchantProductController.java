package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CreateProductRequest;
import com.example.dto.ProductResponse;
import com.example.dto.UpdateProductRequest;
import com.example.service.MerchantProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/merchant/products")
@RequiredArgsConstructor
public class MerchantProductController
{

	@Autowired
	private MerchantProductService service;
	
	@PostMapping
	public ResponseEntity<ProductResponse> create(@RequestHeader("X-Merchant-id")String merchantId,@Valid @RequestBody CreateProductRequest request)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				             .body(service.createProduct(merchantId, request));
	}
	
	@GetMapping("/{productId}")
	public ProductResponse getById(@RequestHeader("X-Merchant-id")String merchantId, @PathVariable Long productId)
	{
		return service.getProduct(merchantId,productId);
	}
	/*
	@GetMapping
	public List<ProductResponse> getByAll(@RequestHeader("X-Merchant-id") String merchantId)
	{
		return service.getProductAll(merchantId);
		
	}
	*/
	
	@PutMapping("/{productId}")
	public ProductResponse updateProduct(
			         @RequestHeader("X-Merchant-id")String merchantId,
			         @PathVariable Long productId,
	                 @Valid @RequestBody UpdateProductRequest request)
	{
		return service.updateProduct(merchantId,productId,request);
		
	}
	
	@DeleteMapping("/{productId}")
	public void deleteProduct(@RequestHeader("X-Merchant-id")String merchantId,
			         @PathVariable Long productId)
	{
		 service.deleteProduct(merchantId,productId);
		
	}
	
   
	@GetMapping
	public ResponseEntity<Page<ProductResponse>> getProducts(
	        @RequestHeader("X-Merchant-id") String merchantId,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    return ResponseEntity.ok(
	            service.getProductAllWithPage(merchantId, page, size)
	    );
	}
	
	

}
