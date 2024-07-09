package com.ayoungmk.orders_sibs.service;

import java.util.List;

import com.ayoungmk.orders_sibs.exception.UsersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.UserDTO;

public interface UsersService {
	
	public List<UserDTO> findAll();
	public UserDTO findById(Long id) throws UsersNotFoundException;
	public UserDTO save(UserDTO usersDto);
	public UserDTO updateUsers(Long id, UserDTO usersDtoDetails) throws UsersNotFoundException;
	public void deleteById(Long id) throws UsersNotFoundException;
}
