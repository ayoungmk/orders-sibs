package com.ayoungmk.orders_sibs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.ItemDTO;
import com.ayoungmk.orders_sibs.service.impl.ItemServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orderSibs/v1/itens")
public class ItensController {

	Logger logger = LoggerFactory.getLogger(ItensController.class);
	
	private ItemServiceImpl itemServiceImpl;

	@Autowired
	public ItensController (ItemServiceImpl itensServiceImpl) {
		this.itemServiceImpl = itensServiceImpl;
	}
	
	@GetMapping
	public ResponseEntity<List<ItemDTO>> getAllItens(){
		return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemDTO> getItensById(@PathVariable Long id) throws ItensNotFoundException{
		return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<ItemDTO> createItens(@RequestBody @Valid ItemDTO itemDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(itemServiceImpl.save(itemDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemDTO> updateItens(@PathVariable Long id, @RequestBody ItemDTO itemDtoDetails){
		return ResponseEntity.status(HttpStatus.OK).body(itemServiceImpl.updateItens(id, itemDtoDetails));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteItens(@PathVariable Long id){
		itemServiceImpl.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Removed successfully!");
	}
}