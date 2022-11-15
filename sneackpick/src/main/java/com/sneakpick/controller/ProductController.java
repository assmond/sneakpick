package com.sneakpick.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sneakpick.entities.Brand;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.Size;
import com.sneakpick.services.ProductService;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> productList(Model model) {
		List<Product> products = productService.findAll();
		return products;
	}

	@GetMapping("/{id}")
	public Product product(@PathVariable Long id) {
		Product product = productService.findProductById(id);
		return product;
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public Product store(@RequestParam(required = false, value = "image1") MultipartFile image1,
			@RequestParam(required = false, value = "image2") MultipartFile image2,
			@RequestParam(required = false, value = "image3") MultipartFile image3,
			@RequestParam(required = false, value = "name") String name, @RequestParam("brand") String brandParam,
			@RequestParam(required = false, value = "size") String sizePram,
			@RequestParam(required = false, value = "price") double price,
			@RequestParam(required = false, value = "description") String description) throws IOException {

		Product pr = new Product();
		pr.setName(name);
		pr.setDescription(description);
		pr.setPrice(price);

		if (!brandParam.isEmpty()) {
			pr.setBrand(Brand.builder().id(Long.parseLong(brandParam)).build());
		}

		Set<Size> sizes = new HashSet<>();
		List<String> items = Arrays.asList(sizePram.split("\\s*,\\s*"));

		if (items.size() > 0) {
			for (String sizeId : items) {
				sizes.add(Size.builder().id(Long.parseLong(sizeId)).build());
			}

		}

		if (image1 != null) {
			pr.setImage(image1.getBytes());
		}

		if (image2 != null) {
			pr.setImage2(image2.getBytes());
		}
		if (image3 != null) {
			pr.setImage3(image3.getBytes());
		}
		Product p = productService.saveProduct(pr);
		return p;

	}

	@RequestMapping("/add")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("allSizes", productService.getAllSizes());
		model.addAttribute("allBrands", productService.getAllBrands());
		model.addAttribute("allCategories", productService.getAllCategories());
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {

		productService.saveProduct(product);
		return "redirect:product-list";
	}

	@RequestMapping("/edit")
	public String editProduct(@RequestParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		return "editProduct";
	}

	@GetMapping(path = { "/image1/{id}" })
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

		Product product = productService.findProductById(id);
		if (product.getImage() != null) {
			return ResponseEntity.ok().body(product.getImage());
		}
		return null;
	}

	@GetMapping(path = { "/image2/{id}" })
	public ResponseEntity<byte[]> getImage2(@PathVariable("id") Long id) throws IOException {
		Product product = productService.findProductById(id);
		if (product.getImage2() != null) {
			return ResponseEntity.ok().body(product.getImage2());
		}
		return null;
	}

	@GetMapping(path = { "/image3/{id}" })
	public ResponseEntity<byte[]> getImage3(@PathVariable("id") Long id) throws IOException {
		Product product = productService.findProductById(id);
		if (product.getImage3() != null) {
			return ResponseEntity.ok().body(product.getImage3());
		}
		return null;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editProductPost(@ModelAttribute("product") Product product, HttpServletRequest request) {

		productService.saveProduct(product);
		return "redirect:product-list";
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProductById(id);
	}

}
