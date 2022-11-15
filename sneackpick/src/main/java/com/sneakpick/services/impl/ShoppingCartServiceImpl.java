package com.sneakpick.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sneakpick.entities.CartItem;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.ShoppingCart;
import com.sneakpick.entities.User;
import com.sneakpick.repositories.CartItemRepository;
import com.sneakpick.services.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public ShoppingCart getShoppingCart(User user) {
		return new ShoppingCart(cartItemRepository.findAllByUserAndOrderIsNull(user));
	}

	@Override
	@Cacheable("itemcount")
	public int getItemsNumber(User user) {
		return cartItemRepository.countDistinctByUserAndOrderIsNull(user);
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) {
		Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
		return opt.get();
	}

//	@Override
//	public CartItem addProductToShoppingCart(Product product, User user, int qty, String size) {
//		ShoppingCart shoppingCart = this.getShoppingCart(user);
//		CartItem cartItem = shoppingCart.findCartItemByProductAndSize(product.getId(), size);
//		if (cartItem != null && cartItem.hasSameSizeThan(size)) {
//			cartItem.addQuantity(qty);
//			cartItem.setSize(size);
//			cartItem = cartItemRepository.save(cartItem);
//		} else {
//			cartItem = new CartItem();
//			cartItem.setUser(user);
//			cartItem.setProduct(product);
//			cartItem.setQty(qty);
//			cartItem.setSize(size);
//			cartItem = cartItemRepository.save(cartItem);
//		}
//		return cartItem;
//	}

	@Override
	public CartItem addProductToShoppingCart(Product product, User user, String size, Date from, Date to) {
		ShoppingCart shoppingCart = this.getShoppingCart(user);
		CartItem cartItem = shoppingCart.findCartItemByProductAndSize(product.getId(), size);
		if (cartItem != null && cartItem.hasSameSizeThan(size)) {
			cartItem.setSize(size);
			cartItem = cartItemRepository.save(cartItem);
		} else {
			cartItem = new CartItem();
			cartItem.setUser(user);
			cartItem.setProduct(product);
			cartItem.setDateStart(from);
			cartItem.setDateEnd(to);
			cartItem.setSize(size);
			cartItem = cartItemRepository.save(cartItem);
		}
		return cartItem;
	}

	@Override
	@CacheEvict(value = "itemcount", allEntries = true)
	public void removeCartItem(CartItem cartItem) {
		cartItemRepository.deleteById(cartItem.getId());
	}

	@Override
	public void updateCartItem(CartItem cartItem, Integer qty) {
//		if (qty == null || qty <= 0) {
//			this.removeCartItem(cartItem);
//		} else if (cartItem.getProduct().hasStock(qty)) {
////			cartItem.setQty(qty);
//			cartItemRepository.save(cartItem);
//		}
		cartItemRepository.save(cartItem);
	}

	@Override
	@CacheEvict(value = "itemcount", allEntries = true)
	public void clearShoppingCart(User user) {
		cartItemRepository.deleteAllByUserAndOrderIsNull(user);
	}
}
