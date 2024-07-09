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

import com.ayoungmk.orders_sibs.exception.StockNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockDTO;
import com.ayoungmk.orders_sibs.service.impl.StockServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderSibs/v1/stock")
public class StockController {
	
	private StockServiceImpl stockServiceImpl;

	@Autowired
	public StockController (StockServiceImpl stockServiceImpl) {
		this.stockServiceImpl = stockServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<StockDTO>> getAllStock(){
		return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StockDTO> getStockById(@PathVariable Long id) throws StockNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<StockDTO> createStock(@RequestBody @Valid StockDTO stockDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(stockServiceImpl.save(stockDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @RequestBody StockDTO stockDtoDetails){
		return ResponseEntity.status(HttpStatus.OK).body(stockServiceImpl.updateStock(id, stockDtoDetails));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStock(@PathVariable Long id){
			stockServiceImpl.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removed successfully!");
	}
}