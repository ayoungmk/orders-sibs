package com.ayoungmk.orders_sibs.model.dto;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDTO {

	private Timestamp creationDate;
	private String itemName;
	private Long quantity;
}
