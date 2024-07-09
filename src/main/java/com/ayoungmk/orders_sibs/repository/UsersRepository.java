package com.ayoungmk.orders_sibs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoungmk.orders_sibs.model.entity.User;

public interface UsersRepository extends JpaRepository<User, Long>{

    User findByName (String name);

}
