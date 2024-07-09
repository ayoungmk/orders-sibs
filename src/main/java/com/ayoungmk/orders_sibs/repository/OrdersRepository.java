package com.ayoungmk.orders_sibs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoungmk.orders_sibs.model.entity.Item;
import com.ayoungmk.orders_sibs.model.entity.Order;

public interface OrdersRepository extends JpaRepository<Order, Long>{
	
	
	List<Order> findByStatusAndItemIdOrderByCreationDateAsc(String status, Item item);

	List<Order> findAllByStatus(String status);
}
