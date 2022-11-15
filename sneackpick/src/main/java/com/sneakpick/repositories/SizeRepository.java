package com.sneakpick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sneakpick.entities.Size;

public interface SizeRepository extends JpaRepository<Size, Long> {

	Size findByValue(String value);
}
