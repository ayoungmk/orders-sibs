package com.ayoungmk.orders_sibs.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
	@NotBlank(message="O nome deve ser informado")
	@Size(min=2, message="O nome deve ter no mínimo 2 caracteres")
	private String name;
}
