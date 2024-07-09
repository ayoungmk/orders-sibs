package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.StockDTO;
import com.ayoungmk.orders_sibs.model.entity.Stock;

@Component
public class StockMapper {

	private ModelMapper mapper;
	
	@Autowired
	public StockMapper (ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public Stock toEntity(StockDTO dto) {
		Stock entity = mapper.map(dto, Stock.class);
		return entity;
	}
	
	public StockDTO toDTO(Stock entity) {
		StockDTO dto = mapper.map(entity, StockDTO.class);
		return dto;
	}
	
	public List<StockDTO> toDTO(List<Stock> stocks){
		return stocks.stream()
				.map(stock -> toDTO(stock))
				.collect(Collectors.toList());
	}
	
}
