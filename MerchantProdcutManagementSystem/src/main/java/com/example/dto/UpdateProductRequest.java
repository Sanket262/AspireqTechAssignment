package com.example.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class UpdateProductRequest {
	
	private String description;
	
	@DecimalMin(value="0.01", message="Price Must be Greater Than Zero")
	private BigDecimal price;
	
	private Boolean isAvailable;

}
