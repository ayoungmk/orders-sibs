package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.StockNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockDTO;
import com.ayoungmk.orders_sibs.model.entity.Item;


public interface StockService {
	
	public List<StockDTO> findAll();
	public StockDTO findById(Long id) throws StockNotFoundException;
	public StockDTO save(StockDTO stockDto);
	public StockDTO updateStock(Long id, StockDTO stockDtoDetails) throws StockNotFoundException;
	public void deleteById(Long id) throws StockNotFoundException;
	public Long findIdbyItens(Item item);
}
