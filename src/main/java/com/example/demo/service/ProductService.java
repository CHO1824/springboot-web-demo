package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import java.util.List;

public interface ProductService {
	List<ProductDto> getProducts(ProductDto dto);
}