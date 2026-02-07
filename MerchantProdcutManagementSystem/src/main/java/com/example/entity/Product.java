package com.example.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Data
@Setter
@Getter
public class Product 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 private Long id;
	
	@Column(name="merchant_id")
	private String merchantId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private BigDecimal price;
	
	private boolean isAvailable=true;
	
	private boolean isDeleted=false;
	
	private LocalDateTime createdAt;
	
	@PrePersist
	void onCreate()
	{
		this.createdAt=LocalDateTime.now();
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}


	
	
	 
}
