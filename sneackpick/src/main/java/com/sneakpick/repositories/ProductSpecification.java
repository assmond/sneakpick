package com.sneakpick.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sneakpick.entities.Brand;
import com.sneakpick.entities.Product;
import com.sneakpick.entities.Size;

public class ProductSpecification {

	private ProductSpecification() {
	}

	@SuppressWarnings("serial")
	public static Specification<Product> filterBy(Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> brands, String search) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				query.distinct(true);
				if (sizes != null && !sizes.isEmpty()) {
					Join<Product, Size> joinSize = root.join("sizes");
					predicates.add(criteriaBuilder.and(joinSize.get("value").in(sizes)));
				}

				if (brands != null && !brands.isEmpty()) {
					Join<Product, Brand> joinSize = root.join("brands");
					predicates.add(criteriaBuilder.and(joinSize.get("name").in(brands)));
				}

				if (search != null && !search.isEmpty()) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "%" + search + "%")));
				}
				if (priceLow != null && priceLow >= 0) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow)));
				}
				if (priceHigh != null && priceHigh >= 0) {
					predicates
							.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh)));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
