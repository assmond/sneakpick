package com.sneakpick.services;

import java.util.List;

import com.sneakpick.entities.Size;

public interface SizeService {

	List<Size> findAll();

	Size findById(Long id);

	Size findByValue(String value);

	Size save(Size size);
}
