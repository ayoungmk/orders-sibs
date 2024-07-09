package com.ayoungmk.orders_sibs.repository;

import com.ayoungmk.orders_sibs.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{

	Item findByName(String name);
}
