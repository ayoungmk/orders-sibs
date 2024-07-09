package com.ayoungmk.orders_sibs.controller;

import java.util.List;

import com.ayoungmk.orders_sibs.model.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayoungmk.orders_sibs.exception.OrdersNotFoundException;
import com.ayoungmk.orders_sibs.service.impl.OrdersServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderSibs/v1/orders")
public class OrdersController {

	private OrdersServiceImpl ordersServiceImpl;
	
	@Autowired
	public OrdersController (OrdersServiceImpl ordersServiceImpl) {
		this.ordersServiceImpl = ordersServiceImpl;
	}
	
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		return ResponseEntity.status(HttpStatus.OK).body(ordersServiceImpl.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrdersById(@PathVariable Long id) throws OrdersNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(ordersServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderDTO orderDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(ordersServiceImpl.createOrder(orderDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> updateOrders(@PathVariable Long id, @RequestBody OrderDTO orderDtoDetails){
		return ResponseEntity.status(HttpStatus.OK).body(ordersServiceImpl.updateOrders(id, orderDtoDetails));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrders(@PathVariable Long id){
			ordersServiceImpl.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removed successfully!");
	}
}