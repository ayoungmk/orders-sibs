package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayoungmk.orders_sibs.model.dto.ItemDTO;
import com.ayoungmk.orders_sibs.model.entity.Item;

@Component
public class ItemMapper {
	
	private ModelMapper mapper;
	
	@Autowired
	public ItemMapper (ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public Item toEntity(ItemDTO dto) {
		Item entity = mapper.map(dto, Item.class);
		return entity;
	}
	
	public ItemDTO toDTO(Item entity) {
		ItemDTO dto = mapper.map(entity, ItemDTO.class);
		return dto;
	}
	
	public List<ItemDTO> toDTO(List<Item> itens){
		return itens.stream()
				.map(item -> toDTO(item))
				.collect(Collectors.toList());
	}
	
}
