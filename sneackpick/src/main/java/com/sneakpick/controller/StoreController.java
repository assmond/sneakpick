package com.sneakpick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sneakpick.entities.Product;
import com.sneakpick.form.ProductFilterForm;
import com.sneakpick.services.ProductService;

@RestController
public class StoreController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/store")
	public List<Product> store(@ModelAttribute("filters") ProductFilterForm filters, Model model) {
		List<Product> list = productService.findProductsByCriteria(filters.getPricelow(), filters.getPricehigh(),
				filters.getSize(), filters.getBrand(), filters.getSearch());
		return list;
	}

}
