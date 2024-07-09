package com.ayoungmk.orders_sibs.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.ayoungmk.orders_sibs.model.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ayoungmk.orders_sibs.model.entity.User;

@Component
public class UserMapper {
	
	private ModelMapper mapper;
	
	@Autowired
	public UserMapper (ModelMapper mapper) {
		this.mapper = mapper;
	}
	
	public User toEntity(UserDTO dto) {
		User entity = mapper.map(dto, User.class);
		return entity;
	}
	
	public UserDTO toDTO(User entity) {
		UserDTO dto = mapper.map(entity, UserDTO.class);
		return dto;
	}
	
	public List<UserDTO> toDTO(List<User> users){
		return users.stream()
				.map(user -> toDTO(user))
				.collect(Collectors.toList());
	}
	
}
