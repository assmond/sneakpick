package com.sneakpick.services.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sneakpick.entities.Product;
import com.sneakpick.repositories.ProductRepository;
import com.sneakpick.repositories.ProductSpecification;
import com.sneakpick.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private final Path root = Paths.get("products/images");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for images!");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files!");
		}
	}

	@Autowired
	private ProductRepository productRepository;

	@Value("${productservice.featured-items-number}")
	private int featuredProductsNumber;

	@Override
	public List<Product> findAllProducts() {
		return null;
//		return productRepository.findAllEagerBy();
	}

	@Override
	public List<Product> findProductsByCriteria(Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> brands, String search) {
		List<Product> list = productRepository
				.findAll(ProductSpecification.filterBy(priceLow, priceHigh, sizes, brands, search));
		return list;
	}

	@Override
	public List<Product> findFirstProducts() {
		return productRepository.findAll(PageRequest.of(0, featuredProductsNumber)).getContent();
	}

	@Override
	public Product findProductById(Long id) {
		Optional<Product> opt = productRepository.findById(id);
		return opt.get();
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	@CacheEvict(value = { "sizes", "categories", "brands" }, allEntries = true)
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return null;
//		return productRepository.findAllSizes();
	}

	@Override
	@Cacheable("brands")
	public List<String> getAllBrands() {
		return null;
//		return productRepository.findAllBrand();
	}

	@Override
	public List<String> getAllCategories() {
		return null;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
}
