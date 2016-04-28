package com.robot.order.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.robot.models.Menu;
import com.robot.models.Order;
import com.robot.models.OrderHistory;
import com.robot.models.repositories.CustomerRepository;
import com.robot.models.repositories.MenuRepository;
import com.robot.models.repositories.OrderHistoryRepository;
import com.robot.models.repositories.OrderRepository;

@RestController
@RequestMapping("/ordering")
public class OrderRobotController {

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderHistoryRepository orderHistoryRepository;

	@RequestMapping(value = "/showMenus", method = RequestMethod.GET)
	public List<Menu> showMenus() {
		List<Menu> menus = menuRepository.findAll();
		return menus;
	}

	@RequestMapping(value = "/showHistroy/{customerId}", method = RequestMethod.GET)
	public List<OrderHistory> getHistory(@PathVariable(value = "customerId") String customerId) {
		List<OrderHistory> orderHistory = orderHistoryRepository.findByCustomerId(customerId);
		return orderHistory;
	}

	@RequestMapping(value = "/order/{customerId}", method = RequestMethod.POST)
	public String placeOrder(@PathVariable(value = "customerId") String customerId,
			@RequestParam(value = "courses") String[] courses) {
		Order order = new Order();
		order.setCustomerid(customerId);
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		order.setDate(dateFormat.format(now));
		order.setCourses(courses);
		this.orderRepository.save(order);
		return null;
	}
}
