package com.sneakpick.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sneakpick.entities.Order;
import com.sneakpick.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);

	@EntityGraph(attributePaths = { "cartItems", "payment", "shipping" })
	Order findEagerById(Long id);

}
