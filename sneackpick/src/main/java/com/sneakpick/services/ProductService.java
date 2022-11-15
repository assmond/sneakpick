package com.sneakpick.services;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.sneakpick.entities.Product;

public interface ProductService {

	public void init();

	public void save(MultipartFile file);

	public Resource load(String filename);

	public void deleteAll();

	public Stream<Path> loadAll();

	List<Product> findAll();

	List<Product> findAllProducts();

	List<Product> findProductsByCriteria(Integer priceLow, Integer priceHigh, List<String> sizes, List<String> brands,
			String search);

	List<Product> findFirstProducts();

	Product findProductById(Long id);

	Product saveProduct(Product product);

	void deleteProductById(Long id);

	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

}
