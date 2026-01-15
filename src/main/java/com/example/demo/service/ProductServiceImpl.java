package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	
	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<ProductDto> getProducts(ProductDto dto) {
		return repository.selectProductList(dto);
	}
}