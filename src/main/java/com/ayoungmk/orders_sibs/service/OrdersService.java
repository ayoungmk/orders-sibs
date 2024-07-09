package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.OrdersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.OrderDTO;

public interface OrdersService {
	
	public List<OrderDTO> findAll();
	public OrderDTO findById(Long id) throws OrdersNotFoundException;

	public OrderDTO createOrder(OrderDTO orderDto);
	public OrderDTO updateOrders(Long id, OrderDTO orderDtoDetails) throws OrdersNotFoundException;
	public void deleteById(Long id) throws OrdersNotFoundException;
}
