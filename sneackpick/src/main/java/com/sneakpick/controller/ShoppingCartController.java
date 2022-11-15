package com.sneakpick.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.dto.ShoppingCartDto;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.ShoppingCart;
import com.sneakpick.entities.User;
import com.sneakpick.services.ProductService;
import com.sneakpick.services.ShoppingCartService;
import com.sneakpick.services.UserService;

@RestController
@RequestMapping("api/v1/shopping-cart")
public class ShoppingCartController {

	@Autowired
	private ProductService produitService;
	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/cart")
	public ShoppingCart shoppingCart(Principal p) {
		User user = userService.findByEmail(p.getName());
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);
		ShoppingCartDto cartDto = new ShoppingCartDto();
		cartDto.setCartItems(shoppingCart.getCartItems());
		cartDto.setShopingCart(shoppingCart);
		return shoppingCart;
	}

	@RequestMapping(value = "/add-item")
	public Product addItem(@RequestParam(value = "product", required = false) String productId,
			@RequestParam(value = "qty", required = false) String qty,
			@RequestParam(value = "size", required = false) String size, Principal principal,
			@RequestParam(value = "from") String dateFromString, @RequestParam(value = "to") String dateToString)
			throws ParseException {
		Product product = produitService.findProductById(Long.parseLong(productId));
		Date dateFrom = null;
		if (!dateFromString.isEmpty()) {
			dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(dateFromString);
		}
		Date dateTo = null;
		if (!dateToString.isEmpty()) {
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(dateToString);
		}
		User user = userService.findByEmail(principal.getName());
		shoppingCartService.addProductToShoppingCart(product, user, size, dateFrom, dateTo);
		return product;
	}

	@DeleteMapping("/remove-item/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeItem(@PathVariable("id") Long id) {
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));
	}
}
