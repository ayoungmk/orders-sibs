package com.ayoungmk.orders_sibs.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoungmk.orders_sibs.exception.ItensNotFoundException;
import com.ayoungmk.orders_sibs.exception.OrdersNotFoundException;
import com.ayoungmk.orders_sibs.model.dto.OrderDTO;
import com.ayoungmk.orders_sibs.model.dto.mapper.OrderMapper;
import com.ayoungmk.orders_sibs.model.entity.Item;
import com.ayoungmk.orders_sibs.model.entity.Order;
import com.ayoungmk.orders_sibs.model.entity.Stock;
import com.ayoungmk.orders_sibs.model.entity.StockMovement;
import com.ayoungmk.orders_sibs.model.entity.User;
import com.ayoungmk.orders_sibs.model.enums.StatusOrder;
import com.ayoungmk.orders_sibs.repository.ItemRepository;
import com.ayoungmk.orders_sibs.repository.OrdersRepository;
import com.ayoungmk.orders_sibs.repository.StockMovementsRepository;
import com.ayoungmk.orders_sibs.repository.StockRepository;
import com.ayoungmk.orders_sibs.repository.UsersRepository;
import com.ayoungmk.orders_sibs.service.EmailService;
import com.ayoungmk.orders_sibs.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	private OrdersRepository ordersRepository;
	private UsersRepository usersRepository;
	private StockRepository stockRepository;
	private ItemRepository itemRepository;
	private OrderMapper ordersMapper;
	private StockMovementsRepository stockMovementsServiceImpl;
	private EmailService emailServiceImpl;
	
	@Autowired
    public OrdersServiceImpl(OrdersRepository ordersRepository, OrderMapper ordersMapper, UsersRepository usersRepository,
    		StockRepository stockRepository, StockMovementsRepository stockMovementsServiceImpl, EmailService emailServiceImpl,
    		ItemRepository itemRepository) {
        this.ordersRepository = ordersRepository;
        this.ordersMapper = ordersMapper;
        this.usersRepository = usersRepository;
        this.stockRepository = stockRepository;
        this.stockMovementsServiceImpl = stockMovementsServiceImpl;
        this.emailServiceImpl = emailServiceImpl;
        this.itemRepository = itemRepository;
    }

	Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

	public List<OrderDTO> findAll(){
		return ordersMapper.toDTO( ordersRepository.findAll());
	}

	public OrderDTO findById(Long id) throws OrdersNotFoundException{
		Optional<Order> order = ordersRepository.findById(id);
		if(order.isPresent()) {
			return ordersMapper.toDTO(order.get());
		}else {
			logger.error("Order with id " + id + " not found!");
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}


	public void deleteById(Long id) throws OrdersNotFoundException {
		ordersRepository.deleteById(id);
	}

	public OrderDTO updateOrders(Long id, OrderDTO orderDtoDetails) throws OrdersNotFoundException{
		Optional<Order> orderOpt = ordersRepository.findById(id);
		if(orderOpt.isPresent()) {
			Order order = orderOpt.get();
			order.getItemId().setName(orderDtoDetails.getItemName());
			order.setQuantity(orderDtoDetails.getQuantity());
			ordersRepository.save(order);
			return orderDtoDetails;
		}else {
			logger.error("Order with id " + id + " not found!");
			throw new ItensNotFoundException("Order with id " + id + " not found!");
		}
	}

	public OrderDTO createOrder(OrderDTO orderDto) {
		Item item = itemRepository.findByName(orderDto.getItemName());
		Stock stock = stockRepository.findByItem(itemRepository.findByName(orderDto.getItemName()));
		User user = usersRepository.findByName(orderDto.getUserName());
		Order order = new Order();
		order.setCreationDate(new Timestamp(System.currentTimeMillis()));
		order.setItemId(item);
		order.setUserId(user);
		order.setQuantity(orderDto.getQuantity());
		this.assignStatusOrder(order,stock,orderDto.getQuantity(),item, user);
		ordersRepository.save(order);
		orderDto.setStatus(order.getStatus());
		return orderDto;
	}

	public void assignStatusOrder(Order order, Stock stock,Long quantity,Item item, User user) {
		if (quantity <= stock.getQuantity()) {
			stockMovementsServiceImpl.save(StockMovement.builder().creationDate(new Timestamp(System.currentTimeMillis())).item(item).quantity(-order.getQuantity()).build());
			stock.setQuantity(stock.getQuantity() - order.getQuantity());
			stockRepository.save(stock);
			logger.info("Stock after completing order: " + stock.toString());
			this.emailServiceImpl.sendSimpleMessage(user.getEmail(), "Order Complete", "Your order was successfully completed!");
			order.setStatus(StatusOrder.COMPLETE.toString());
			logger.info("Order Completed: " + order.toString());
		} else {
			order.setStatus(StatusOrder.INCOMPLETE.toString());
		}
	}
	
	public List<Order> getIncompleteOrders(Item item) {
		List<Order> incompleteOrders = ordersRepository.findByStatusAndItemIdOrderByCreationDateAsc(StatusOrder.INCOMPLETE.toString(), item);
		return incompleteOrders;
	}
}
