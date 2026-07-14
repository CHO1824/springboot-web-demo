package com.example.demo.repository;

import com.example.demo.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductRepository {
	List<ProductDto> selectProductList(ProductDto dto);
}