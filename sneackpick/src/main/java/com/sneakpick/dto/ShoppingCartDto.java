package com.sneakpick.dto;

import java.util.ArrayList;
import java.util.List;

import com.sneakpick.entities.CartItem;
import com.sneakpick.entities.ShoppingCart;

import lombok.Data;

@Data
public class ShoppingCartDto {
	List<CartItem> items = new ArrayList<>();
	ShoppingCart shoppingCart;

	public void setCartItems(List<CartItem> cartItems) {
		this.items = cartItems;

	}

	public void setShopingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;

	}

}
