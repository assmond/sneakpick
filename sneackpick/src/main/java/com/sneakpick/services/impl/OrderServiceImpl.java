package com.sneakpick.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sneakpick.entities.CartItem;
import com.sneakpick.entities.Order;
import com.sneakpick.entities.Payment;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.Shipping;
import com.sneakpick.entities.ShoppingCart;
import com.sneakpick.entities.User;
import com.sneakpick.repositories.CartItemRepository;
import com.sneakpick.repositories.OrderRepository;
import com.sneakpick.repositories.ProductRepository;
import com.sneakpick.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public synchronized Order createOrder(ShoppingCart shoppingCart, Shipping shipping, Payment payment, User user) {
		Order order = new Order();
		order.setUser(user);
		order.setPayment(payment);
		order.setShipping(shipping);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shipping.setOrder(order);
		payment.setOrder(order);
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("In Progress");

		order = orderRepository.save(order);

		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Product product = item.getProduct();
//			product.decreaseStock(item.getQty());
			productRepository.save(product);
			item.setOrder(order);
			cartItemRepository.save(item);
		}
		return order;
	}

	@Override
	public Order findOrderWithDetails(Long id) {
		return orderRepository.findEagerById(id);
	}

	@Override
	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

	@Override
	@Transactional
	public synchronized Order createOrder(ShoppingCart shoppingCart, User user) {
		Order order = new Order();
		order.setUser(user);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("In Progress");
		order.setDateEnd(shoppingCart.getDateEnd());
		order.setDateStart(shoppingCart.getDateStart());
		order = orderRepository.save(order);
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Product product = item.getProduct();
			productRepository.save(product);
			item.setOrder(order);
			cartItemRepository.save(item);
		}
		return order;
	}

	@Override
	public void deleteById(Long id) {
		this.orderRepository.deleteById(id);
	}

}
