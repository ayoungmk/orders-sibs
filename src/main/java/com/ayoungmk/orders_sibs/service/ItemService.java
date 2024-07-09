package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.ItemDTO;


public interface ItemService {

	public List<ItemDTO> findAll();
	public ItemDTO findById(Long id) throws ItensNotFoundException;
	public ItemDTO save(ItemDTO itemDto);
	public ItemDTO updateItens(Long id, ItemDTO itemDtoDetails) throws ItensNotFoundException;
	public void deleteById(Long id) throws ItensNotFoundException;
}
