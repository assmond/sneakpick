package com.sneakpick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.entities.Brand;
import com.sneakpick.services.BrandService;

@RestController
@RequestMapping("api/v1/brands")
public class BrandController {
	@Autowired
	private BrandService brandService;

	@GetMapping
	public List<Brand> productList(Model model) {
		List<Brand> brands = brandService.findAll();
		return brands;
	}

	@GetMapping("/{id}")
	public Brand product(@PathVariable Long id) {
		Brand brand = brandService.findById(id);
		return brand;
	}
}
