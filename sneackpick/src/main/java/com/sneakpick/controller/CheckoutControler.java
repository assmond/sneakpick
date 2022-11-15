package com.sneakpick.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.entities.ShoppingCart;
import com.sneakpick.entities.User;
import com.sneakpick.services.OrderService;
import com.sneakpick.services.ShoppingCartService;
import com.sneakpick.services.UserService;

@RestController
@RequestMapping("api/v1/checkout")
public class CheckoutControler {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@PostMapping(value = "/checkout")
	@ResponseStatus(HttpStatus.CREATED)
	public void placeOrder(Principal p) {
		User user = userService.findByEmail(p.getName());
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
		if (!shoppingCart.isEmpty()) {
			orderService.createOrder(shoppingCart, user);
		}
	}

}
