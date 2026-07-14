package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<ProductDto> list(ProductDto dto) {
		// 1. 값이 null이 아니고 비어있지 않은지 안전하게 체크
		boolean hasStartDate = dto.getStartDate() != null && !dto.getStartDate().trim().isEmpty();
		boolean hasEndDate = dto.getEndDate() != null && !dto.getEndDate().trim().isEmpty();
		
		if (hasStartDate && hasEndDate) {
			// 2. 백엔드 방어 코드: 시작일이 종료일보다 늦으면 바로 빈 리스트 반환 (NullPointerException 방지)
			if (dto.getStartDate().compareTo(dto.getEndDate()) > 0) {
				return Collections.emptyList();
			}
			
			// 3. 방어 코드: 커넥션 풀 고사 및 JVM 메모리 보호를 위한 최대 3개월 기간 제한
			try {
				LocalDate start = LocalDate.parse(dto.getStartDate());
				LocalDate end = LocalDate.parse(dto.getEndDate());
				long months = ChronoUnit.MONTHS.between(start, end);
				if (months > 3) {
					throw new IllegalArgumentException("최대 3개월까지만 조회할 수 있습니다.");
				}
			} catch (Exception e) {
				// 날짜 형식이 올바르지 않게 넘어온 경우 에러 방지
				return Collections.emptyList();
			}
		}
		
		return service.getProducts(dto);
	}
}