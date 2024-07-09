package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ayoungmk.orders_sibs.model.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ayoungmk.orders_sibs.model.entity.Order;

@Component
public class OrderMapper {

	private ModelMapper mapper;
	
	@Autowired
	public OrderMapper (ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public Order toEntity(OrderDTO dto) {
		Order entity = mapper.map(dto, Order.class);
		return entity;
	}
	
	public OrderDTO toDTO(Order entity) {
		OrderDTO dto = mapper.map(entity, OrderDTO.class);
		return dto;
	}
	
	public List<OrderDTO> toDTO(List<Order> orders){
		return orders.stream()
				.map(order -> toDTO(order))
				.collect(Collectors.toList());
	}
	
}
