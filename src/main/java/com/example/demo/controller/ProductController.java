package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<ProductDto> list(
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate
	) {
		ProductDto dto = new ProductDto();
		dto.setStartDate(startDate);
		dto.setEndDate(endDate);
		return service.getProducts(dto);
	}
}
