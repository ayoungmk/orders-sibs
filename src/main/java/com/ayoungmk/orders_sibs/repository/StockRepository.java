package com.ayoungmk.orders_sibs.repository;

import com.ayoungmk.orders_sibs.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoungmk.orders_sibs.model.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	Stock findByItem(Item item);
}
