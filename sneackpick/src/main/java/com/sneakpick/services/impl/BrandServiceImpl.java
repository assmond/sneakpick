package com.sneakpick.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakpick.entities.Brand;
import com.sneakpick.repositories.BrandRepository;
import com.sneakpick.services.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository repo;

	@Override
	public List<Brand> findAll() {

		return repo.findAll();
	}

	@Override
	public Brand findById(Long id) {

		return repo.findById(id).get();
	}

	@Override
	public Brand findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public Brand save(Brand brand) {
		return repo.save(brand);
	}

}
