package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.StockMovementsNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockMovementDTO;

public interface StockMovementsService {
	
	public List<StockMovementDTO> findAll();
	public StockMovementDTO findById(Long id) throws StockMovementsNotFoundException;
	public StockMovementDTO save(StockMovementDTO stockMovementDto);
	public StockMovementDTO updateStockMovements(Long id, StockMovementDTO stockMovementDtoDetails) throws StockMovementsNotFoundException;
	public void deleteById(Long id) throws StockMovementsNotFoundException;
}
