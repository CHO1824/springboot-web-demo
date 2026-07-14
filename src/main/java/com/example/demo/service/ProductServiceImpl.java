package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true) // 단순 조회의 성능 최적화를 위한 어노테이션 추가
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<ProductDto> getProducts(ProductDto dto) {
		// 혹시 모를 NullPointerException 방어 코드
		if (dto == null) {
			return Collections.emptyList();
		}
		return productRepository.selectProductList(dto);
	}
}