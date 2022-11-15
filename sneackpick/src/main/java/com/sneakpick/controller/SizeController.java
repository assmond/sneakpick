package com.sneakpick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.entities.Size;
import com.sneakpick.services.SizeService;

@RestController
@RequestMapping("api/v1/sizes")
public class SizeController {
	@Autowired
	private SizeService sizeService;

	@GetMapping
	public List<Size> productList(Model model) {
		List<Size> sizes = sizeService.findAll();
		return sizes;
	}

	@GetMapping("/{id}")
	public Size product(@PathVariable Long id) {
		Size size = sizeService.findById(id);
		return size;
	}
}
