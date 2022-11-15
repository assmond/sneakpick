package com.sneakpick.services;

import java.util.List;

import com.sneakpick.entities.Brand;

public interface BrandService {
	List<Brand> findAll();

	Brand findById(Long id);

	Brand findByName(String name);

	Brand save(Brand brand);
}
