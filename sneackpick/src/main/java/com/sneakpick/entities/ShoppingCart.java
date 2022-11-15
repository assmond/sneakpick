package com.sneakpick.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShoppingCart {

	private List<CartItem> cartItems;
	private Date dateStart;
	private Date dateEnd;

	public ShoppingCart(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public BigDecimal getGrandTotal() {
		BigDecimal cartTotal = new BigDecimal(0);
		for (CartItem item : this.cartItems) {
//			cartTotal = cartTotal.add(item.getSubtotal());
			cartTotal = cartTotal.add(new BigDecimal(item.getProduct().getPrice()));
			dateStart = item.getDateStart();
			dateEnd = item.getDateEnd();
		}
		return cartTotal;
	}

	public boolean isEmpty() {
		return cartItems.isEmpty();
	}

	public void removeCartItem(CartItem cartItem) {
		cartItems.removeIf(item -> item.getId() == cartItem.getId());
	}

	public void clearItems() {
		cartItems.clear();
	}

	public CartItem findCartItemByProductAndSize(Long id, String size) {
		for (CartItem item : this.cartItems) {
			if (item.getProduct().getId().equals(id) && item.getSize().equals(size)) {
				return item;
			}
		}
		return null;
	}

	public int getItemCount() {
		return this.cartItems.size();
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

}
