package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ayoungmk.orders_sibs.model.dto.StockMovementDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ayoungmk.orders_sibs.model.entity.StockMovement;

@Component
public class StockMovementMapper {
	
	private ModelMapper mapper;
	
	@Autowired
	public StockMovementMapper (ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public StockMovement toEntity(StockMovementDTO dto) {
		StockMovement entity = mapper.map(dto, StockMovement.class);
		return entity;
	}
	
	public StockMovementDTO toDTO(StockMovement entity) {
		StockMovementDTO dto = mapper.map(entity, StockMovementDTO.class);
		return dto;
	}
	
	public List<StockMovementDTO> toDTO(List<StockMovement> stockMovements){
		return stockMovements.stream()
				.map(stockMovement -> toDTO(stockMovement))
				.collect(Collectors.toList());
	}
	
}
