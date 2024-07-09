package com.ayoungmk.orders_sibs.controller;

import java.util.List;

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

import com.ayoungmk.orders_sibs.exception.StockMovementsNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockMovementDTO;
import com.ayoungmk.orders_sibs.service.impl.StockMovementsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderSibs/v1/stockMovements")
public class StockMovementsController {
	
	private StockMovementsServiceImpl stockMovementsServiceImpl;

	@Autowired
	public StockMovementsController (StockMovementsServiceImpl stockMovementsServiceImpl) {
		this.stockMovementsServiceImpl = stockMovementsServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<StockMovementDTO>> getAllStockMovements(){
		return ResponseEntity.status(HttpStatus.OK).body(stockMovementsServiceImpl.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StockMovementDTO> getStockMovementsById(@PathVariable Long id) throws StockMovementsNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(stockMovementsServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<StockMovementDTO> createStockMovements(@RequestBody @Valid StockMovementDTO stockMovementDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(stockMovementsServiceImpl.save(stockMovementDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StockMovementDTO> updateStockMovements(@PathVariable Long id, @RequestBody StockMovementDTO stockMovementDtoDetails){
		return ResponseEntity.status(HttpStatus.OK).body(stockMovementsServiceImpl.updateStockMovements(id, stockMovementDtoDetails));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStockMovements(@PathVariable Long id){
			stockMovementsServiceImpl.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removed successfully!");
	}
}