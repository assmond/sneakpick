package com.sneakpick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneakpick.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

//	@EntityGraph(value = "UserComplete", type = EntityGraphType.FETCH)
//	User findByUsername(String username);

	User findByEmail(String email);

	boolean existsByEmail(String email);

}
