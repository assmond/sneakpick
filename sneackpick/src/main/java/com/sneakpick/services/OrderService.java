package com.sneakpick.services;

import java.util.List;

import com.sneakpick.entities.Order;
import com.sneakpick.entities.Payment;
import com.sneakpick.entities.Shipping;
import com.sneakpick.entities.ShoppingCart;
import com.sneakpick.entities.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);

	Order createOrder(ShoppingCart shoppingCart, User user);

	List<Order> findByUser(User user);

	Order findOrderWithDetails(Long id);

	void deleteById(Long id);
}
