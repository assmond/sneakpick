package com.sneakpick.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sneakpick.entities.CartItem;
import com.sneakpick.entities.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@EntityGraph(attributePaths = { "product" })
	List<CartItem> findAllByUserAndOrderIsNull(User user);

	void deleteAllByUserAndOrderIsNull(User user);

	int countDistinctByUserAndOrderIsNull(User user);

	List<CartItem> findByOrderId(Long id);
}
