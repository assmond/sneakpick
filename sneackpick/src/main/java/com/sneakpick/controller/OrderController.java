package com.sneakpick.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.entities.CartItem;
import com.sneakpick.entities.Order;
import com.sneakpick.entities.User;
import com.sneakpick.repositories.CartItemRepository;
import com.sneakpick.services.OrderService;
import com.sneakpick.services.UserService;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

	@Autowired
	private UserService userService;
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private OrderService orderService;

	@GetMapping()
	public List<Order> myOrders(Principal p) {
		User user = userService.findByEmail(p.getName());
		List<Order> orders = orderService.findByUser(user);
		return orders;
	}

	@RequestMapping("/order-detail")
	public String orderDetail(@RequestParam("order") Long id, Model model) {
		Order order = orderService.findOrderWithDetails(id);
		model.addAttribute("order", order);
		return "orderDetails";
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable("id") Long id) {
		for (CartItem item : cartItemRepository.findByOrderId(id)) {
			cartItemRepository.deleteById(item.getId());
		}
		orderService.deleteById(id);
	}

	@GetMapping("/{id}")
	public Order getOrder(@PathVariable("id") Long id) {
		Order order = orderService.findOrderWithDetails(id);
		return order;
	}
}
