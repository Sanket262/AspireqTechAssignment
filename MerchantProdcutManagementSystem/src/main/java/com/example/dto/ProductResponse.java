package com.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ProductResponse 
{
	private Long id;
	private String productName;
	private String description;
	private BigDecimal price;
	private Boolean isAvailable;
	private LocalDateTime createdAt;

}
