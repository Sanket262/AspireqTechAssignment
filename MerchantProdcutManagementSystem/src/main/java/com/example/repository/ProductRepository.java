package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.ProductResponse;
import com.example.entity.Product;
import org.springframework.data.domain.Pageable;


public interface ProductRepository extends JpaRepository<Product, Long> {

	    boolean existsByMerchantIdAndProductNameIgnoreCase(
	            String merchantId,
	            String productName
	    );
	    
	  //  “Give me the product ONLY IF it belongs to this merchant.”
	    
	  
		List<Product> findAllByMerchantIdAndIsDeleted(String merchantId,boolean isDeleted);

		Optional<Product> findByIdAndIsDeletedFalse(Long productId);
		


		    Page<Product> findAllByMerchantIdAndIsDeleted(
		            String merchantId,
		            boolean isDeleted,
		            Pageable pageable
		    );
		

	}
