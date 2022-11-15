package com.sneakpick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sneakpick.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

//	@EntityGraph(attributePaths = { "sizes", "categories", "brand" })
//	List<Product> findAllEagerBy();
//
//	@Override
//	@EntityGraph(attributePaths = { "sizes", "categories", "brand" })
//	Optional<Product> findById(Long id);
//
//	@Query("SELECT DISTINCT s.value FROM Size s")
//	List<String> findAllSizes();
//
//	@Query("SELECT DISTINCT b.name FROM Brand b")
//	List<String> findAllBrand();

}
