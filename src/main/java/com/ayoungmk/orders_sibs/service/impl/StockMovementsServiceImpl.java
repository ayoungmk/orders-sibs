package com.ayoungmk.orders_sibs.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.StockMovementsNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.StockDTO;
import com.ayoungmk.orders_sibs.model.dto.StockMovementDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.StockMovementMapper;
import com.ayoungmk.orders_sibs.model.entity.Order;
import com.ayoungmk.orders_sibs.model.entity.Stock;
import com.ayoungmk.orders_sibs.model.entity.StockMovement;
import com.ayoungmk.orders_sibs.model.enums.StatusOrder;
import com.ayoungmk.orders_sibs.repository.ItemRepository;
import com.ayoungmk.orders_sibs.repository.OrdersRepository;
import com.ayoungmk.orders_sibs.repository.StockMovementsRepository;
import com.ayoungmk.orders_sibs.repository.StockRepository;
import com.ayoungmk.orders_sibs.service.StockMovementsService;

@Service
public class StockMovementsServiceImpl implements StockMovementsService {
	
	private StockMovementsRepository stockMovementsRepository;
	private StockRepository stockRepository;
	private ItemRepository itemRepository;
	private OrdersRepository orderRepository;
	private StockMovementMapper stockMovementsMapper;
	private OrdersServiceImpl ordersServiceImpl;
	private StockServiceImpl stockServiceImpl;
	
	@Autowired
    public StockMovementsServiceImpl (StockMovementsRepository stockMovementsRepository, StockRepository stockRepository, ItemRepository itemRepository,
    		StockMovementMapper stockMovementsMapper, OrdersServiceImpl ordersServiceImpl, StockServiceImpl stockServiceImpl, OrdersRepository orderRepository) {
        this.stockMovementsRepository = stockMovementsRepository;
        this.stockRepository = stockRepository;
        this.itemRepository = itemRepository;
        this.stockMovementsMapper = stockMovementsMapper;
        this.ordersServiceImpl = ordersServiceImpl;
        this.stockServiceImpl = stockServiceImpl;
        this.orderRepository = orderRepository;
    }

	Logger logger = LoggerFactory.getLogger(StockMovementsServiceImpl.class);
	
	public List<StockMovementDTO> findAll(){
		List<StockMovement> stockMovements = stockMovementsRepository.findAll();
		List<StockMovementDTO> stockMovementsDto = stockMovementsMapper.toDTO(stockMovements);
		return stockMovementsDto;
	}

	public StockMovementDTO findById(Long id) throws StockMovementsNotFoundException {
		Optional<StockMovement> stockMovements = stockMovementsRepository.findById(id);
		if(stockMovements.isPresent()) {
			StockMovementDTO stockMovementDto = stockMovementsMapper.toDTO(stockMovements.get());
			return stockMovementDto;
		}else {
			logger.error("Stock Movement with id " + id + " not found!");
			throw new ItensNotFoundException("Stock Movement with id " + id + " not found!");
		}
	}

	@Override
	public StockMovementDTO save(StockMovementDTO stockMovementDto) {
		StockMovement stockMovement = new StockMovement();
		stockMovement.setItem(itemRepository.findByName(stockMovementDto.getItemName()));
		stockMovement.setCreationDate(new Timestamp(System.currentTimeMillis()));
		stockMovement.setQuantity(stockMovementDto.getQuantity());
		stockMovementsRepository.save(stockMovement);
		logger.info("Stock Movement created: " + stockMovement.toString());
		Stock stock = stockRepository.findByItem(stockMovement.getItem());
		stockServiceImpl.updateStock(stock.getId(), StockDTO.builder().itemName(stock.getItem().getName()).quantity(stock.getQuantity()+stockMovementDto.getQuantity()).build());
		logger.info("Stock before trying to complete orders: " + stock.toString());
		
		List<Order> incompleteOrders = ordersServiceImpl.getIncompleteOrders(stockMovement.getItem());
		for (Order order : incompleteOrders) {
			ordersServiceImpl.assignStatusOrder(order,stock, order.getQuantity(),stock.getItem(), order.getUserId());
		}
		return stockMovementDto;
	}


	public void deleteById(Long id) throws StockMovementsNotFoundException {
		Optional<StockMovement> stockMovementsOpt = stockMovementsRepository.findById(id);
		if(stockMovementsOpt.isPresent()) {
			StockMovement stockMovement = stockMovementsOpt.get();
			stockMovementsRepository.delete(stockMovement);
		}else {
			logger.error("Stock Movement with id " + id + " not found!");
			throw new ItensNotFoundException("Stock Movement with id " + id + " not found!");
		}
	}

	public StockMovementDTO updateStockMovements(Long id, StockMovementDTO stockMovementsDtoDetails) throws StockMovementsNotFoundException {
		Optional<StockMovement> stockMovementsOpt = stockMovementsRepository.findById(id);
		if(stockMovementsOpt.isPresent()) {
			StockMovement stockMovement = stockMovementsOpt.get();
//			stockMovement.setItem(stockMovementsDtoDetails.getItem());
			stockMovement.setQuantity(stockMovementsDtoDetails.getQuantity());
			stockMovementsRepository.save(stockMovement);
			StockMovementDTO stockMovementDto = stockMovementsMapper.toDTO(stockMovement);
			return stockMovementDto;
		}else {
			logger.error("Stock Movement with id " + id + " not found!");
			throw new ItensNotFoundException("Stock Movement with id " + id + " not found!");
		}
	}

	public List<StockMovementDTO> getStockMovementsOfOrder(Long idOrder) {
		List<Order> order = orderRepository.findAllByStatus(StatusOrder.COMPLETE.toString());
		
		return null;
	}
}
