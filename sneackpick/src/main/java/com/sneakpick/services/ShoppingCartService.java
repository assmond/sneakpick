package com.sneakpick.services;

import java.util.Date;

import com.sneakpick.entities.CartItem;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.ShoppingCart;
import com.sneakpick.entities.User;

public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);

	int getItemsNumber(User user);

	CartItem findCartItemById(Long cartItemId);

//	CartItem addProductToShoppingCart(Product product, User user, int qty, String size);

	CartItem addProductToShoppingCart(Product product, User user, String size, Date from, Date to);

	void clearShoppingCart(User user);

	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);

}
