package com.sneakpick.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sneakpick.entities.Size;
import com.sneakpick.repositories.SizeRepository;
import com.sneakpick.services.SizeService;

@Service
public class SizeServiceImpl implements SizeService {

	@Autowired
	private SizeRepository repo;

	@Override
	public List<Size> findAll() {

		return repo.findAll();
	}

	@Override
	public Size findById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Size findByValue(String value) {
		return repo.findByValue(value);
	}

	@Override
	public Size save(Size size) {
		return repo.save(size);
	}

}
